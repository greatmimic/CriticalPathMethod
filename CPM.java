package cs3310;


public class CPM {

	public static void findCPM() {
		//#of tasks
		int n = 13;
		
		//insert A[][] adj matrix with edges as 1
		int[][] A = new int[20][20];
		A[0][1] = 1;
		A[0][2] = 1;
		A[0][3] = 1;
		A[1][4] = 1;
		A[1][5] = 1;
		A[2][5] = 1;
		A[3][5] = 1;
		A[4][6] = 1;
		A[5][6] = 1;
		A[5][7] = 1;
		A[3][8] = 1;
		A[6][9] = 1;
		A[7][9] = 1;
		A[8][10] = 1;
		A[8][11] = 1;
		A[9][12] = 1;
		A[10][12] = 1;
		A[11][12] = 1;
		
		
		int ES[] = new int[20];
		int EF[] = new int[20];
		int LS[] = new int[20];
		int LF[] = new int[20];
		int slack[] = new int[20];
		
		
		for(int i=0; i<n; i++) {
			ES[i] = 0;
			EF[i] = 0;
			LS[i] = 0;
			LF[i] = 0;
			slack[i] = 0;
		}
		//task time array
		int T[] = {2,4,5,9,3,2,1,10,11,6,9,8,7};
		
		//forward pass
		for (int i=0; i<n; i++) {
			int maxPredecessor = 0;
			for(int j=0; j<n; j++) {
				if (A[j][i] == 1) {
					maxPredecessor = Math.max(maxPredecessor, EF[j]);
				}
			}
			ES[i] = maxPredecessor;
			EF[i] = ES[i] + T[i];
		}
		
		//backward pass
		for (int i=n-1; i>=0; i--) {
			int minSuccessor = EF[12];
			for(int j=0; j<n; j++) {
				if (A[i][j] == 1) {
					minSuccessor = Math.min(minSuccessor, LS[j]);
				}
			}
			LF[i] = minSuccessor;
			LS[i] = LF[i] - T[i];
		}
		
		//slack = lf - ef
		for (int i=0; i<n; i++) {
			slack[i] = LF[i] - EF[i];
		}
		//print ES EF LS LF SLACK
		for (int i=0; i<n; i++) {
			System.out.println("Task-" + i + ": ES=" + ES[i] + ", EF=" + EF[i] + ", LS=" + LS[i] + ", LF=" + LF[i] + ", Slack="+ slack[i]);
		}
		
		//find which tasks are critical based on zero slack
		System.out.println("Critical Tasks: ");
		for (int i=0; i<n; i++) {
			if(slack[i] == 0)
			{
				System.out.print(i+" ");
			}
		}
		
		
	}

	
	public static void main(String[] args) {
		 CPM.findCPM();
		
	}
}
