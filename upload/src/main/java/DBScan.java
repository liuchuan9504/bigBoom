

import com.egoo.db.druid.connector.DBConnectionPool;
import com.egoo.db.druid.connector.MySQLConnector;
import com.egoo.druid.config.DBConfigure;
import com.egoo.local.config.ConfigInfo;

import java.sql.ResultSet;
import java.sql.SQLException;


//** Created by liuchuan on 2017/7/4.
public class DBScan {

    public static UpLoad up = new UpLoad() ;
    public static MySQLConnector mySQLConnector = (MySQLConnector) DBConnectionPool.getDBConnector();

    public static void DB_Upload() throws Exception {
        System.out.println("qqqqqqq");
        RecordInfo ri = new RecordInfo();
        ResultSet rs  =  mySQLConnector.select("SELECT * FROM record_info where is_location ='1';");
        //mySQLConnector .closeConnection();
        System.out.print("+++++"+rs.next());
        System.out.println("kkkkk");
       while(rs.next()){
           System.out.println("sssss");
           ri.setRecord_id(rs.getString(6));
           ri.setRecord_url(rs.getString(7));

           System.out.println("数据提取"+ri.getRecord_id()+" "+ri.getRecord_url());

           String str = up.fastDFSUploadFile(ri.getRecord_url());
           int i = str.indexOf("http://");
           str = str.substring(i,str.length()-2);

           if(mySQLConnector.update("update record_info set record_url = '"+str+"',is_location = '0' where record_id = '"+ri.getRecord_id()+"'")){
               System.out.println("上传成功");

           }


       }
        mySQLConnector .closeConnection();
       return ;
    }

}
