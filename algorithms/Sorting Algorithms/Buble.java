public class Buble
{
    public static void exch(int[]a, int i, int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void sort(int[] a, int size)
    {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size-1-i; j++)
            {
                if(a[j]>a[j+1])
                    exch(a,j,j+1);
            }
        }
    }
}