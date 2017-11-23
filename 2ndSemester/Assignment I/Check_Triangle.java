
public class Check_Triangle
{


    public static final int ACUTE_TRI = 2;
    public static final int RIGHT_ANGLED_TRI = 1;
    public static final int OBTUSE_TRI = 3;
    public static final double TINY_DIFF = 0.0001;
    public static final int NOT_TRI = 0;

     static boolean form_triangle(double[] ls)
    {
       return 2*max(ls) < sum(ls);
    }

    static int kind_triangle(double[] ls)
    {
    	int max = max(ls);
    	int min = min(ls);
    	int mid = max-min;
    	if(max*max - ((min*min)+(mid*mid))==0){
    		return 1;
    	}
    	else if(max*max - ((min*min)+(mid*mid))<0){
    		return 2;
    	}
    	else if(max*max - ((min*min)+(mid*mid))>0){
    		return 3;
    	}
        return NOT_TRI;
    }


    static double sum(double[] arr)
    {
        double result = 0;
        for (int k = 0; k < arr.length; k++)
            result += arr[k];
        return result;
    }

    static double max(double[] arr)
    {
        int j = 0;
        int k = arr.length- 1;
        while ( j < k ) {
            if (arr[j] < arr[k])
                j++;
            else
                k--;
        }
        return arr[j];
    }

    static double min(double[] arr)
    {
        int j = 0;
        int k = arr.length - 1;
        while ( j < k )
            if (arr[j] > arr[k])
                j++;
            else
                k--;
        return arr[j];
    }
}
