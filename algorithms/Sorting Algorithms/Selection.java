public class Selection
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
            int min=i;
            for(int j=i+1;j<size;j++)
            {
                if(a[j]<a[min])
                    min=j;
            }
            exch(a,i,min);
        }
    }
}