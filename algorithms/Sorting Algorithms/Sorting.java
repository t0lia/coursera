class Sorting{
    public static void printArray(int[] a, int size)
    {
    System.out.print("\t");
         for(int i=0; i<size;i++)
        {
           System.out.print(a[i]);
           System.out.print(" ");
        }
    System.out.print("\n");
    }
    public static void main(String[] args)
    {
        int a[];
        int size=10;
        a=new int[size];
        
      //  for(int i=0; i<size;i++)
      //     a[i]=StdRandom.uniform(90)+10;
        a[0]=84;
        a[1]=79;
        a[2]=86;
        a[3]=44;
        a[4]=32;
        a[5]=65;
        a[6]=83;
        a[7]=19;
        a[8]=56;
        a[9]=94;
        
        printArray(a,size);
        //Buble.sort(a,size);
        //printArray(a,size);
        Shell.sort(a,size);
        printArray(a,size);
    }
}