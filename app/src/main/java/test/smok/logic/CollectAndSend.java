package test.smok.logic;

import java.util.LinkedList;


/**
 * Created by Kuba on 25.03.2018.
 */

public class CollectAndSend implements Runnable {

    LinkedList<DataManager> mDataManagerList;
    LinkedList <String>  mData; //tymczasowo, docelowo ta klasa co zbierze, parsuje do XML i wysyła na serwer

    public CollectAndSend(LinkedList<String> linkedList){//ta zostaje
        this.mDataManagerList = new LinkedList<>();
        this.mData = linkedList;
    }

    public void addDataManager(DataManager dataManager){//ta zostaje
        this.mDataManagerList.add(dataManager);
    }

    private void collectFromDataManagers(){//testowo, metoda prawdopodobnie nie bedzie potrzebna
        for(DataManager dataManager:this.mDataManagerList) {
            mData.add(dataManager.collectfromCollectors());
        }
    }

    private void send(){//ta będzie potrzebna

    }

    @Override
    public void run() {//tu na razie też tylko testowo
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while(true){
            try{
                collectFromDataManagers();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
