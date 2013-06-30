package core.math;

import java.math.*;

public class Matrix
{
  private int r;//rows
  private int c;//columns
  private BigDecimal[][] matrix;
  
  public Matrix(int r,int c)//creates a Matrix object with null values, with the specified dimensions
  {
    this.matrix = new BigDecimal[r][c];
    this.r = r;
    this.c = c;
  }
  
  public Matrix(BigDecimal[][] matrix)//creates a Matrix object with the values contained in the two dimensional array
  {
    this.matrix = matrix;
    this.c = matrix[0].length;
    this.r = matrix.length;
  }
  
  public Matrix add(Matrix m)//computes the sum of this Matrix and the input matrix
  {
    if(!this.getSize().equals(m.getSize()))
      return null;
    else
    {
      Matrix output = new Matrix(this.r, this.c);
      for(int i=1;i<=this.r;i++)
        for(int j=1;j<=this.c;j++)
        output.setEntry(i,j,this.getEntry(i,j).add(m.getEntry(i,j)));
      return output;
    }
  }
  
  public Matrix subtract(Matrix m)//computes the difference of this Matrix and the input matrix
  {
    return this.add(m.additiveInverse());
  }
  
  public Matrix multiplyByScalar(BigDecimal scalar)//returns scalar*matrix
  {
    Matrix output = new Matrix(this.r, this.c);
    for(int i=1;i<=this.r;i++)
      for(int j=1;j<=this.c;j++)
      output.setEntry(i,j,this.getEntry(i,j).multiply(scalar));
    return output;
  }
  
  public Matrix multiply(Matrix b)//computes the matrix product of this matrix and b
  {
    if(b.getColumnSize()==this.c)
    {
      Matrix output = new Matrix(this.r,b.getRowSize());
      for(int i=1;i<=output.getColumnSize();i++)
        for(int j=1;j<=output.getRowSize();j++)
          output.setEntry(i,j,Matrix.dotProduct(this.getRow(i).toArray(),b.getColumn(j).toArray()));
      return output;
    }
    else
      return null;
  }
  
  public Matrix power(int p)//computes the matrix raised to the power p
  {
    if(p == 1)
      return this;
    else if(p == -1)
    {
      if(this.isInvertible())
        return this.inverse();
      else return null;
    }
    else
    {
      if((p%2)==0)
        return this.power(p/2).multiply(this.power(p/2));
      else
        return this.power(p/2).multiply(this.power(p/2)).multiply(this);
    }
  }
  
  public Matrix inverse()//computes the inverse of the matrix using the formula inverse = (1/determinant) times adjoint
  {
    if(this.isInvertible())
    {
      try
      {
        return this.adjoint().multiplyByScalar(BigDecimal.ONE.divide(this.determinant()));
      }
      catch(ArithmeticException e)
      {
        return this.adjoint().multiplyByScalar(BigDecimal.ONE.divide(this.determinant(),20,BigDecimal.ROUND_HALF_UP));
      }
    }
    else
      return null;
  }
  
  public Matrix transpose()//returns the transpose of this Matrix
  {
    Matrix output = new Matrix(this.c,this.r);
    for(int i=1;i<=this.r;i++)
      for(int j=1;j<=this.c;j++)
      output.setEntry(j,i,this.getEntry(i,j));
    return output;
  }
  
  public BigDecimal determinant()//computes the determinant of the matrix by calculating the cofactor expansion along the first row
  {
    if(this.isSquare())
    {
      if(this.r == 1 && this.c == 1)
        return this.matrix[0][0];
      else
      {
        BigDecimal output = BigDecimal.ZERO;
        for(int i =0;i<this.c;i++)
          output = output.add((new BigDecimal("-1").pow(i)).multiply(this.matrix[0][i]).multiply(this.subMatrix(1,i+1).determinant()));
        return output;
      }
    }
    else
      return BigDecimal.ZERO;
  }
  
  public Matrix cofactorsMatrix()//computes the matrix of cofactors of this matrix
  {
    Matrix output = new Matrix(this.r,this.c);
    for(int i=1;i<=this.r;i++)
      for(int j=1;j<=this.c;j++)
      output.setEntry(i,j,this.cofactor(i,j));
    return output;
  }
  
  public BigDecimal cofactor(int i,int j)//computes the i,jth cofactor
  {
    return (new BigDecimal("-1").pow(i+j)).multiply(this.subMatrix(i,j).determinant());
  }
  
  public Matrix adjoint()//computes the adjoint of this matrix, which is equal to the transpose of the matrix of cofactors
  {
    return this.cofactorsMatrix().transpose();
  }
  
  public boolean isInvertible()//returns whether the matrix is invertible or not
  {
    if(this.determinant().compareTo(BigDecimal.ZERO) == 0)
      return false;
    else
      return true;
  }
  
  public Matrix subMatrix(int row,int column)//returns the matrix without the ith row and jth column
  {
    Matrix output = new Matrix(this.r-1,this.c-1);
    int i = 1;
    int j = 1;
    for(int m=1;m<=this.r;m++)
    {
      j=1;
      if(m==row)
        continue;
      for(int n=1;n<=this.c;n++)
      {
        if(n==column)
          continue;
        output.setEntry(i,j,this.getEntry(m,n));
        j++;
      }
      i++;
    }
    return output;
  }
  
  public Matrix additiveInverse()//returns -(matrix)
  {
    return this.multiplyByScalar(new BigDecimal("-1"));
  }
  
  public boolean swapRows(int i, int j)//swaps the ith and jth rows
  {
    try
    {
      BigDecimal[] temp = this.matrix[i-1];
      this.matrix[i-1] = this.matrix[j-1];
      this.matrix[j-1] = temp;
      return true;
    }
    catch(Exception e){return false;}
  }
  
  public boolean multiplyRowByScalar(int row,BigDecimal scalar)//mutliplies the row by the given scalar
  {
    try
    {
      for(int i=0;i<this.getRowSize();i++)
        this.matrix[row-1][i] = this.matrix[row-1][i].multiply(scalar);
      return true;
    }
    catch(Exception e){return false;}
  }
  
  public Matrix getRow(int i)//returns the ith row in the from of a Matrix object
  {
    BigDecimal[][] output = new BigDecimal[1][c];
    output[0] = this.matrix[i-1];
    return new Matrix(output);
  }
  
  public Matrix getColumn(int j)//returns the jth column in the form of a Matrix object
  {
    BigDecimal[][] output = new BigDecimal[r][1];
    for(int i=0;i<this.r;i++)
      output[i][0] = this.matrix[i][j-1];
    return new Matrix(output);
  }
  
  public boolean setRow(int r, BigDecimal[] row)//sets the row with index r to the given array
  {
    try
    {
      this.matrix[r-1] = row;
      return true;
    }
    catch(Exception e){return false;}
  }
  
  public BigDecimal getEntry(int i, int j)//returns the i,jth entry of the matrix
  {
    return this.matrix[i-1][j-1];
  }
  
  public boolean setEntry(int i, int j,BigDecimal value)//sets the i,jth entry of the matrix to the specified value
  {
    try
    {
      this.matrix[i-1][j-1] = value;
      return true;
    }
    catch(Exception e){return false;}
  }
  
  public boolean isSquare()//returns whether the Matrix object represents a square matrix or not
  {
    return (this.r==this.c);
  }
  
  public boolean isIdentity()//returns whether the Matrix object is equal to the identity or not
  {
    if(!this.isSquare())
      return false;
    else
      return (this.equals(Matrix.getIdentity(this.r)));
  }
  
  public boolean isRowVector()//returns whether the Matrix object represents a row vector or not
  {
    return (this.r==1);
  }
  
  public boolean isColumnVector()//returns whether the Matrix object represents a column vector or not
  {
    return (this.c==1);
  }
  
  public boolean isVector()//returns whether the Matrix object represents a column matrix or not
  {
    return (this.isColumnVector() || this.isRowVector());
  }
  
  public int getRowSize()//returns the number of columns, or the size of rows
  {
    return this.c;
  }
  
  public int getColumnSize()//returns the number of rows, or the size of columns
  {
    return this.r;
  }
  
  public String getSize()//returns the string "rxc"
  {
    return this.r+"x"+this.c;
  }
  
  public String toString()//returns a String represenation of the Matrix object
  {
    String output = "";
    for(int i=1;i<=this.r;i++)
    {
      for(int j=1;j<=this.c;j++)
        output += this.getEntry(i,j)+" ";
      if(i!=this.r)
        output += "\n";
    }
    return output;
  }
  
  public BigDecimal[][] toArray()//returns an array representation of the Matrix object
  {
    return this.matrix;
  }
  
  public boolean equals(Matrix input)//returns true is input matrix is equal to matrix, false if not
  {
    if(!input.getSize().equals(this.getSize()))
      return false;
    else
    {
      for(int i=1;i<=this.r;i++)
        for(int j=1;j<=this.c;j++)
          if(this.getEntry(i,j).compareTo(input.getEntry(i,j)) !=0)
          return false;
    }
    return true;
  }
  
  public Matrix clone()//returns a clone representation of the Matrix object
  {
    return new Matrix(this.matrix);
  }
  
  public static Matrix getIdentity(int size)//returns the identity matrix of dimensions size x size
  {
    Matrix output = new Matrix(size,size);
    for(int i=1;i<=size;i++)
      for(int j=1;j<=size;j++)
    {
      if(j==i)
        output.setEntry(i,j,BigDecimal.ONE);
      else
        output.setEntry(i,j,BigDecimal.ZERO);
    }
    return output;
  }
  
  private static BigDecimal dotProduct(BigDecimal[][] row, BigDecimal[][] column)//computes the dot product of a row vector and a column vector
  {
    BigDecimal output = BigDecimal.ZERO;
    for(int i = 0;i<column.length;i++)
      output = output.add(row[0][i].multiply(column[i][0]));
    return output;
  }
}