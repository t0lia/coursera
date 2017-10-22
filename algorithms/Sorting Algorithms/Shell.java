public class Shell
{
public static void sort(int[] a, int size)
{
int N = a.length;
int h = 1;
while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...
while (h >= 1)
{ // h-sort the array.
for (int i = h; i < N; i++)
{
for (int j = i; j >= h && a[j] < a[j-h]; j -= h)
exch(a, j, j-h);

    System.out.print("step:"+i+"\t");
for (int k = 0; k < a.length; k++)
{
    System.out.print(" "+a[k]);
}

    System.out.print("\n");
}
h = h/3;
if(h<2)break;
}
}
private static void exch(int[] a, int i, int j)
{
    int temp=a[i];
    a[i]= a[j];
    a[j]=temp;
}
}