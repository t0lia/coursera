public class Insertion
{
    public static void exch(int[]a, int i, int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void sort(int[] a, int size)
    {
        for(int i=0;i<size;i++)
        {
            for(int j=i;j>0;j--)
            {
                if(a[j-1]>a[j])
                    exch(a,j-1,j);
                else
                    break;
            }
        }
    }
}