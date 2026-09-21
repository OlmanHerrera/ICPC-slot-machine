
/**
 * Write a description of class SlotMachineContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachineContest
{
    public int [][] solve(int n){
        SlotMachine simulator = new SlotMachine(n);
        simulator.spin();
        simulator.spin();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } 
        int[] dp = new int[n+1];
        int[][] solution = new int[n+1][2];
        //preparation
        int k0 = simulator.distinctSymbols();
        
        if(k0 == 1){
            return solution;
        }
        if (k0 == n){
            simulator.spin(1,1);
            k0 = simulator.distinctSymbols();
            if (k0 ==1){
                return solution;
            }
        }
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } 
        if (k0 < n-1){
            for (int i = 1; i <= n; i++){
                if(k0 >= n-1){
                    break;
                }
                simulator.spin(i, 1);
                int k = simulator.distinctSymbols();
                if (k==1){
                    return solution;
                }
                if (k > k0){
                    k0 = k;
                }
                else if(k <k0){
                    simulator.spin(i,n-1);
                    k0 = simulator.distinctSymbols();
                }
            }
        }
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } 
        
        
        //get distance
        for (int i = 1; i <=n; i++){
            int counter = 0;
            int best = -1;
            while (counter < n-1){
                simulator.spin(i,1);
                counter++;
                int k = simulator.distinctSymbols();
                if (k ==1){
                    return solution;
                }
                if (k > best){
                    best = k;
                    dp[i] = counter;
                } 
            }  
            simulator.spin(i,n-counter);
        }
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } 
        //spin each wheel distance
        for (int i = 1; i<= n; i++){
            simulator.spin(i,dp[i]);
            solution[i][0] = i;
            solution[i][1] = dp[i];
        }
        simulator.makeInvisible();
        return solution;
    } 
    public void simulate(int n){
        int[][] solution = solve(n);
        return;
    }
}