package idsa;
import java.util.Random;

public class SP3Chakri {
    public static Random random = new Random();
    public static int numTrials = 50;
    public static void main(String[] args) {
	int n = 10;  int choice = 1 + random.nextInt(4);
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
	if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
	    arr[i] = i;
	}
	Timer timer = new Timer();
	switch(choice) {
	case 1:
		for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSortTake2(arr);
	    }
	    break;
	case 2:
	    for(int i=0; i<numTrials; i++) {
		Shuffle.shuffle(arr);
		mergeSortTake3(arr);
	    }
	    break;  // etc
	}
	timer.end();
	timer.scale(numTrials);

	System.out.println("Choice: " + choice + "\n" + timer);
    }

    public static void insertionSort(int[] arr) {
    }

    public static void mergeSortTake2(int[] a) {
    	int[] b=new int[a.length];
    	mergeSortTake2(a,b,0,a.length-1);
    }
	
	public static void mergeSortTake3(int[] a) {
		int[] b=new int[a.length];
    	mergeSortTake3(a,b,0,a.length-1);
    }
	
	public static void mergeSortTake2(int[] a,int[] b,int i,int k) {
		if(i<k) {
		    int j=(i+k)/2;
		    mergeSortTake2(a,b,i,j);
		    mergeSortTake2(a,b, j+1, j);
		    mergeTake2(a,b,i,j,k);
		 }
	}
	public static void mergeSortTake3(int[] a,int[] b,int i,int k) {
		if(i<k) {
		    int j=(i+k)/2;
		    mergeSortTake3(b,a,i,j);
		    mergeSortTake3(b,a, j+1, j);
		    mergeTake3(a,b,i,j,k);
		 }
	}
	
	public static void mergeTake2(int[] a,int[] b,int i,int j,int k ) {
		System.arraycopy(a, i, b, i, k-i+1);
		int x=i,y=i,z=j+1;
		 while(x<=j&&z<=k) {
		     if(b[x]>b[z]) {
			a[y++]=b[z++];
		     }
		     else {
			 a[y++]=b[x++];
		     }
		 }
		 while(x<=j) {
		     a[y++]=b[x++];
		 }
		 while(z<=k) {
		     a[y++]=b[z++];
		    
		 }
	     }
	public static void mergeTake3(int[] a,int[] b,int i,int j,int k ) {
		int x=i,y=i,z=j+1;
		 while(x<=j&&z<=k) {
		     if(b[x]>b[z]) {
			a[y++]=b[z++];
		     }
		     else {
			 a[y++]=b[x++];
		     }
		 }
		 while(x<=j) {
		     a[y++]=b[x++];
		 }
		 while(z<=k) {
		     a[y++]=b[z++];
		    
		 }
	     }
	}
	
	


   /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

	public void scale(int num) { elapsedTime /= num; }
	
        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }
    
    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    class Shuffle {
	
	public static void shuffle(int[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static<T> void shuffle(T[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static void shuffle(int[] arr, int from, int to) {
	    int n = to - from  + 1;
	    Random random = new Random();
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	public static<T> void shuffle(T[] arr, int from, int to) {
	    int n = to - from  + 1;
	    Random random = new Random();
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}
	
	static<T> void swap(T[] arr, int x, int y) {
	    T tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}

	public static<T> void printArray(T[] arr, String message) {
	    printArray(arr, 0, arr.length-1, message);
	}

	public static<T> void printArray(T[] arr, int from, int to, String message) {
	    System.out.print(message);
	    for(int i=from; i<=to; i++) {
		System.out.print(" " + arr[i]);
	    }
	    System.out.println();
	}
    }
