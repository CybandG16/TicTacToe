package Package;

import java.util.Scanner;

public class TicTacToe_Main {
    static short board[][];
    static int Need=3;
    static int Hmax = 0;
    static int Mmax = 0;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean AgainstMachine = false;
        boolean MachinIsTheFirst = false;

        System.out.println("Hi!\n Would you like to play against machine or an other human?  (print 'M' if you want to play with PC or 'G' if you have an other human)");


        boolean D = false;
        do{
        switch (scan.nextLine().toUpperCase()) {
            case "M" : AgainstMachine = true;
                D=true;
                break;
            case "G" : AgainstMachine = false;
                D=true;
                break;
            default:
                System.out.println("don't kidding me");
        }
        }while(D!=true);
        if(AgainstMachine) System.out.println("I see you wanna play against me!\n Hahaha!\n GL HF)))") ;
        else System.out.println("You're boring((");

        System.out.println("Ok,let's go on.\n How many cells will be on your Board?");
        int Size = 0;
        String size = "";
        do {
            size = scan.nextLine();
            if (isDigit(size)) Size = Integer.parseInt(size);
            else System.out.println("don't kidding me");
        }while (!isDigit(size));
        board = new short[Size][Size];

        for(int i= 0; i<Size;i++)
            for(int j= 0; j<Size;j++)
                board[i][j]=-1;
        System.out.printf("Your board is %d x %d cells." , Size, Size);
        if(Size>3) {
            boolean correct = false;
            System.out.printf("\nNow you have to chose how many signs in a row you need to score to win?(minimum %d, maximum %d) \n", Size>4 ? 5: 3, Size );
            do {
                String L = scan.nextLine();
                if(isDigit(L)) {
                    Need = Integer.parseInt(L);
                    if (Need <= Size & Need >= (Size > 4 ? 5 : 3)) correct = true;
                    else if (Need <= (Size > 4 ? 5 : 3)) System.out.println("I think it will be easy for CROSSES");
                    else System.out.println("It's IMPOSSIBLE");
                }else
                    System.out.println("don't kidding me");
            } while (!correct);

        }
        if(AgainstMachine){
            System.out.println("OK) \n Now decide who will be the firs? (print 'me' if you wanna be the first else print 'you')");
            D = false;
            do{
                String Who = scan.nextLine().toLowerCase();
                switch (Who) {
                    case "me" : MachinIsTheFirst = false;
                        D=true;
                        break;
                    case "you" : MachinIsTheFirst = true;
                        D=true;
                        break;
                    default:
                        System.out.println("don't kidding me");
                }
            }while(D!=true);
        }

        boardInitialization();
        if(MachinIsTheFirst){
            board[(board.length)/2][(board.length)/2]=1;
            boardInitialization();
        }
        GameProcess( AgainstMachine, false,MachinIsTheFirst ? false : true);
    }

    public static void boardInitialization(){
        int Len = board.length*2+1;

        char Cross = 9747;
        char Zero = 9711;
        char LUC = 9487;
        char RUC = 9491;
        char LDC = 9495;
        char RDC = 9499;
        char LCL = 9507;
        char RCL = 9515;
        char UCL = 9523;
        char DCL = 9531;
        char HL = 8212;
        char Ch = 9547;
        char VL = 9475;
        char none = 8270;
        char none2 = 9946;

        for(int i = 0; i<Len; i++)
            for(int j=0; j<Len; j++) {
                if(i==0)
                    {
                        if(j==0) System.out.print("\n"+LUC);
                        else if (j==Len-1) System.out.print(RUC+"\n");
                        else if(j%2==0) System.out.print(UCL);
                        else System.out.print(HL+""+HL);
                    }
                else if(i==Len-1)
                    {
                        if(j==0) System.out.print("\n"+LDC);
                        else if (j==Len-1) System.out.print(RDC+"\n");
                        else if(j%2==0) System.out.print(DCL);
                        else System.out.print(HL+""+HL);
                    }
                else if(i%2==0)
                    {
                        if(j==0) System.out.print("\n"+LCL );
                        else if (j==Len-1) System.out.print(RCL+"\n");
                        else if(j%2==0) System.out.print(Ch);
                        else System.out.print(HL+""+HL);
                    }
                else if(i%2==1)
                    {
                        if(j%2==0) System.out.print(VL);
                        else {
                            if(board[i/2][j/2]==0) System.out.print(none+""+Zero+""+none);
                            else if (board[i/2][j/2]==1) System.out.print(" "+Cross+" ");
                            else System.out.print(" "+none2+" ");
                    }
                }
            }


    }

    public static void GameProcess(boolean AgainstMachina ,boolean MachinasTurn, boolean HumanIsCross){
        boolean finish = false;
        boolean IsDraw = false;
        do {

           if (AgainstMachina) {
               if (MachinasTurn) MachinsTurn(!HumanIsCross);
               else HumansTurn(HumanIsCross);
               MachinasTurn = !MachinasTurn;
           } else {
               HumansTurn(HumanIsCross);
               HumanIsCross = !HumanIsCross;
           }
            IsDraw = DrawCheck();
            if(WinCheck()){
                finish=true;
                boardInitialization();
                if(AgainstMachina)
                    System.out.printf("\nTHE WINNER IS %s\n", MachinasTurn==false ?"'MACHINE(it's me)'":"'HUMAN'");
                else
                    System.out.printf("\nTHE WINNER IS %s\n", HumanIsCross==true?"'ZEROES'":"'CROSSES'");
                break;
            }
           boardInitialization();
       }while (!finish & !IsDraw);

        if(IsDraw)
            System.out.println("Sorry, it's draw)");

        Finish(AgainstMachina, MachinasTurn, HumanIsCross);
    }

    public static void MachinsTurn(boolean Cross){
        String sign = Cross==true? "1": "0";
        String Hsign = Cross==true? "0": "1";
        char MachinassignCH = Cross==true? '1': '0';
        char HumansignCH = Cross==true? '0': '1';

        String[] rows = new String[board.length];
        String[] columns = new String[board.length];
        String[] ULtoRD = new String[2*(board.length-Need)+1];
        String[] URtoLD = new String[2*(board.length-Need)+1];

        //System.out.printf("\n %d %d %d %d ", rows.length, columns.length, ULtoRD.length, URtoLD.length);

        ULtoRD[ULtoRD.length/2]="";
        URtoLD[ULtoRD.length/2]="";
        for(int i=0; i<board.length;i++){
            rows[i] = "";
            columns[i] = "";
            ULtoRD[ULtoRD.length/2]+=board[i][i]==-1? "8": String.valueOf(board[i][i]);
            URtoLD[ULtoRD.length/2]+=(board[i][board.length-i-1])==-1? "8": String.valueOf((board[i][board.length-i-1]));
            for(int j=0; j<board.length; j++){
                rows[i] +=  board[i][j]==-1? "8": String.valueOf(board[i][j]);
                columns[i]+= board[j][i] == -1? "8": String.valueOf(board[j][i]);
            }
        }
        for(int i=0; i<ULtoRD.length/2;i++){
            ULtoRD[i] = "";
            ULtoRD[ULtoRD.length-i-1] = "";
            URtoLD[i] = "";
            URtoLD[ULtoRD.length-i-1] = "";
            for(int j=0; j<Need+i; j++){
                System.out.println(j);
                ULtoRD[i]                   += board[board.length-Need-i+j][j]==-1? "8": String.valueOf(board[board.length-Need-i+j][j]);
                ULtoRD[ULtoRD.length-i-1]   += board[j][board.length-Need-i+j]==-1? "8": String.valueOf(board[j][board.length-Need-i+j]);
                URtoLD[i]                   += board[j][Need+i-1-j]==-1? "8": String.valueOf(board[j][Need+i-1-j]);
                URtoLD[ULtoRD.length-i-1]   += board[Need+i-1-j][j]==-1? "8": String.valueOf(board[Need+i-1-j][j]);
            }
        }

//=====================================ACTION===========================================

        String[] Coords = DangerousSituation(Cross).split(":");

        int I = Integer.parseInt(Coords[2]);
        int StartP = Integer.parseInt(Coords[1]);


        if(Coords[0].equals("C")) {
                StringBuffer SB = new StringBuffer(columns[StartP]);
                String pos = SB.substring(I,I+Need);
                int secCord = pos.indexOf('8')+I;

                board[secCord][StartP] = Short.parseShort(sign);

            } else if(Coords[0].equals("R")) {
                StringBuffer SB = new StringBuffer(rows[StartP]);
                String pos = SB.substring(I,I+Need);
                int secCord = pos.indexOf('8')+I;

                board[StartP][secCord] = Short.parseShort(sign);

            } else if(Coords[0].equals("LR")) {
                int secCord = ULtoRD[StartP].indexOf("8",I);

                if(StartP<ULtoRD.length/2) board[board.length-Need+I][secCord]=Short.parseShort(sign);
                else board[secCord][StartP-ULtoRD.length/2+secCord]=Short.parseShort(sign);

            }else if(Coords[0].equals("RL")) {

                int secCord = URtoLD[StartP].indexOf("8",I);

                if(StartP<URtoLD.length/2) board[secCord][Need-1+StartP-secCord]=Short.parseShort(sign);
                else board[StartP-URtoLD.length/2+secCord][board.length-secCord-1]=Short.parseShort(sign);

            }
    }

    public static void HumansTurn( boolean cross){
        String[] coordinates;
        int[] XY={0, 0};
        boolean Correct=false;
        Scanner scan = new Scanner(System.in);

        System.out.printf("\nturn of %s\n", cross==true? "CROSSES" : "ZEROES");
        System.out.println("print coordinates of your sign (format is ' COLUMN;ROW ' )");
        do {
            coordinates = scan.nextLine().trim().split(";");

            if(coordinates.length==2 && isDigit(coordinates[0].trim()) && isDigit(coordinates[1].trim())) {
                XY[0]=Integer.parseInt(coordinates[0].trim())-1;
                XY[1]=Integer.parseInt(coordinates[1].trim())-1;
                if(XY[0]<0 || XY[0]>=board.length || XY[1]<0 || XY[1]>=board.length)
                    System.out.println(" ! INCORRECT COORDINATES !\n Try again! ");
                else{
                    if(board[XY[1]][XY[0]]==-1)
                    {
                        System.out.println("ok");
                        Correct=true;
                        if(cross) board[XY[1]][XY[0]] = 1; else board[XY[1]][XY[0]]=0;
                    }
                    else System.out.printf("\n! THIS CELL IS ALREADY OCCUPIED BY %s ! \n Try again!", board[XY[1]][XY[0]]==0 ? "'ZEROES'" : "'CROSSES'" );
                }
            }else{
                System.out.println("! WRONG FORMAT !\n Try again!");
            }
        }while(!Correct);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void Finish(boolean AM ,boolean MachinasTurn, boolean HumanIsCross){
        boolean done = false;
        boolean MF = false;
        System.out.println("Would you like to play again? (if you would then print 'YES' else 'NO')");
        Scanner scan = new Scanner(System.in);
           do{
            switch(scan.nextLine()){
                case "YES":
                    if(AM) {
                        System.out.println("Who will start? (print 'me' if you wanna be the first else print 'you')");

                        System.out.println("OK) \n Now decide who will be the first? (print 'me' if you wanna be the first else print 'you')");
                        boolean D = false;
                        do {
                            switch (scan.nextLine()) {
                                case "me":
                                    MF = false;
                                    D = true;
                                    break;
                                case "you":
                                    MF = true;
                                    D = true;
                                    break;
                                default:
                                    System.out.println("don't kidding me");
                            }
                        } while (D != true);
                    }
                    board = new short[board.length][board.length];
                    for(int i= 0; i<board.length;i++)
                        for(int j= 0; j<board.length;j++)
                            board[i][j]=-1;
                    boardInitialization();
                    GameProcess( AM, MF, MF==true ? false : true);
                    done = true;
                    break;
                case "NO" :
                    done = true;
                    break;
                default:
                    System.out.println("don't kidding me");
            }
        }while (!done);
    }

    private static boolean WinCheck(){
        for(int i = 0; i<board.length; i++){
            int times=0;
            int who=board[i][0];

            for(int j = 0; j<board.length; j++){
                if(board[i][j]==who & who!=-1) {
                    times++;
                    if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[i][j];
                }
            }
        }

        for(int i = 0; i<board.length; i++){
            int times=0;
            int who=board[0][i];
            for(int j = 0; j<board.length; j++) {
                if (board[j][i] == who & who != -1){
                    times++;
                     if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[j][i];
                }
            }
        }

        for(int i = 0; i<board.length-Need+1;i++){
            int times=0;
            int who=board[i][0];
            for(int j=i;j<board.length; j++){
                if(board[j][j-i]==who & who!=-1){
                    times++;
                     if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[j][j-i];
                }
            }
            times=0;
            who=board[0][i];

            for(int j=i;j<board.length; j++){
                if (board[j-i][j]==who & who!=-1) {
                    times++;
                    if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[j-i][j];
                }
            }
            times=0;
            who=board[board.length-1-i][0];

            for(int j=board.length-i-1;j>=0; j--){
                if(board[board.length-i-1-j][j]==who & who!=-1) {
                    times++;
                    if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[board.length-i-1-j][j];
                }
            }
            times=0;
            who=board[0][board.length-1-i];

            for(int j=board.length-i-1;j>=0; j--){
                if (board[j][board.length-i-1-j]==who & who!=-1) {
                    times++;
                    if (times == Need) return true;
                }
                else{
                    times=1;
                    who=board[j][board.length-i-1-j];
                }
            }


        }
        return false;
    }

    private static boolean DrawCheck() {
        boolean IsThereAnyPlaces = false;
        if(DangerousSituation(true).equals("")) return !IsThereAnyPlaces;
        else return IsThereAnyPlaces;
    }

    private static String DangerousSituation(boolean Cross){
        String sign = Cross==true? "1": "0";
        String Hsign = Cross==true? "0": "1";
        char MachinassignCH = Cross==true? '1': '0';
        char HumansignCH = Cross==true? '0': '1';

        String[] rows = new String[board.length];
        String[] columns = new String[board.length];
        String[] ULtoRD = new String[2*(board.length-Need)+1];
        String[] URtoLD = new String[2*(board.length-Need)+1];

        ULtoRD[ULtoRD.length/2]="";
        URtoLD[ULtoRD.length/2]="";
        for(int i=0; i<board.length;i++){
            rows[i] = "";
            columns[i] = "";
            ULtoRD[ULtoRD.length/2]+=board[i][i]==-1? "8": String.valueOf(board[i][i]);
            URtoLD[ULtoRD.length/2]+=(board[i][board.length-i-1])==-1? "8": String.valueOf((board[i][board.length-i-1]));
            for(int j=0; j<board.length; j++){
                rows[i] +=  board[i][j]==-1? "8": String.valueOf(board[i][j]);
                columns[i]+= board[j][i] == -1? "8": String.valueOf(board[j][i]);
            }
        }
        for(int i=0; i<ULtoRD.length/2;i++){
            ULtoRD[i] = "";
            ULtoRD[ULtoRD.length-i-1] = "";
            URtoLD[i] = "";
            URtoLD[ULtoRD.length-i-1] = "";
            for(int j=0; j<Need+i; j++){
                ULtoRD[i]                   += board[board.length-Need-i+j][j]==-1? "8": String.valueOf(board[board.length-Need-i+j][j]);
                ULtoRD[ULtoRD.length-i-1]   += board[j][board.length-Need-i+j]==-1? "8": String.valueOf(board[j][board.length-Need-i+j]);
                URtoLD[i]                   += board[j][Need+i-1-j]==-1? "8": String.valueOf(board[j][Need+i-1-j]);
                URtoLD[ULtoRD.length-i-1]   += board[Need+i-1-j][j]==-1? "8": String.valueOf(board[Need+i-1-j][j]);
            }
        }

        //if there is any dangerous situations for machine

        Hmax = 0;
        String StartPosH="";

        for(int i =0; i<board.length; i++){
            StringBuffer newCstr =new StringBuffer(columns[i]);
            StringBuffer newRstr =new StringBuffer(rows[i]);
            for(int j = 0; j<= board.length - Need; j++){
                String strC = newCstr.substring(j,j+Need);
                String strR = newRstr.substring(j,j+Need);
                if(strC.contains(Hsign)){
                    if(!strC.contains(sign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strC.length(); z++){
                            if (strC.charAt(z)==HumansignCH) newMax++;
                        }
                        if(newMax>=Hmax){
                            Hmax=newMax;
                            StartPosH="C:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
                if(strR.contains(Hsign)){
                    if(!strR.contains(sign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strR.length(); z++){
                            if (strR.charAt(z)==HumansignCH) newMax++;
                        }
                        if(newMax>=Hmax){
                            Hmax=newMax;
                            StartPosH="R:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
            }
        }

        for(int i =0; i<ULtoRD.length; i++){
            StringBuffer ULRDstr =new StringBuffer(ULtoRD[i]);
            StringBuffer URLDstr =new StringBuffer(URtoLD[i]);
            for(int j = 0; j<= ULtoRD[i].length() - Need; j++){
                String strLR = ULRDstr.substring(j,j+Need);
                String strRL = URLDstr.substring(j,j+Need);
                if(strLR.contains(Hsign)){
                    if(!strLR.contains(sign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strLR.length(); z++){
                            if (strLR.charAt(z)==HumansignCH) newMax++;
                        }
                        if(newMax>=Hmax){
                            Hmax=newMax;
                            StartPosH="LR:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
                if(strRL.contains(Hsign)){
                    if(!strRL.contains(sign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strRL.length(); z++){
                            if (strRL.charAt(z)==HumansignCH) newMax++;
                        }
                        if(newMax>=Hmax){
                            Hmax=newMax;
                            StartPosH="RL:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
            }
        }

        //if there is any dangerous situations for human

        Mmax =0;
        String StartPosM = "";
        for(int i =0; i<board.length; i++){
            StringBuffer newCstr =new StringBuffer(columns[i]);
            StringBuffer newRstr =new StringBuffer(rows[i]);
            for(int j = 0; j<= board.length - Need; j++){
                String strC = newCstr.substring(j,j+Need);
                String strR = newRstr.substring(j,j+Need);
                if(strC.contains(sign)){
                    if(!strC.contains(Hsign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strC.length(); z++){
                            if (strC.charAt(z)==MachinassignCH) newMax++;
                        }
                        if(newMax>=Mmax){
                            Mmax=newMax;
                            StartPosM="C:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
                if(strR.contains(sign)){
                    if(!strR.contains(Hsign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strR.length(); z++){
                            if (strR.charAt(z)==MachinassignCH) newMax++;
                        }
                        if(newMax>=Mmax){
                            Mmax=newMax;
                            StartPosM="R:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
            }
        }
        for(int i =0; i<ULtoRD.length; i++){
            StringBuffer newULRDstr =new StringBuffer(ULtoRD[i]);
            StringBuffer newURLDstr =new StringBuffer(URtoLD[i]);
            for(int j = 0; j<= ULtoRD[i].length() - Need; j++){
                String strLR = newULRDstr.substring(j,j+Need);
                String strRL = newURLDstr.substring(j,j+Need);
                if(strLR.contains(sign)){
                    if(!strLR.contains(Hsign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strLR.length(); z++){
                            if (strLR.charAt(z)==MachinassignCH) newMax++;
                        }
                        if(newMax>=Mmax){
                            Mmax=newMax;
                            StartPosM="LR:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
                if(strRL.contains(sign)){
                    if(!strRL.contains(Hsign))
                    {
                        int newMax=0;
                        for (int z = 0; z<strRL.length(); z++){
                            if (strRL.charAt(z)==MachinassignCH) newMax++;
                        }
                        if(newMax>=Mmax){
                            Mmax=newMax;
                            StartPosM="RL:"+String.valueOf(i)+":"+String.valueOf(j);
                        }
                    }
                }
            }
        }

        if(Mmax>=Hmax) return StartPosM;
        else return StartPosH;

    }

}
