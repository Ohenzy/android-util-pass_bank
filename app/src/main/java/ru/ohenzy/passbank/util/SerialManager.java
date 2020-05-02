package ru.ohenzy.passbank.util;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import ru.ohenzy.passbank.struct.AccountData;

public class SerialManager {

    private final Context context;
    private final List<AccountData> accountData;

    public SerialManager(Context context) {
        this.context = context;
        accountData = new ArrayList<>();
        if(this.getAccountData() == null)
            this.addRows(new ArrayList<AccountData>());


    }

    public List<AccountData> getAccountData(){
       try {
           ObjectInputStream in = new ObjectInputStream(context.openFileInput("row.list"));
           if(!accountData.isEmpty())
               accountData.clear();
           List<AccountData> list = (List<AccountData>) in.readObject();
           if(list != null)
               accountData.addAll(list);

           in.close();
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return accountData;
   }

   public void addAccount(AccountData account){
        List<AccountData> list = this.getAccountData();
        list.add(account);
        this.addRows(list);
   }

   public void addRows(List<AccountData> accountDataList){
       try {
           ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput("row.list", Context.MODE_PRIVATE));
           out.writeObject(accountDataList);
           out.flush();
           out.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void save() {
        if(!accountData.isEmpty())
            this.addRows(accountData);
    }
}
