import Albert.CfgFileReader.CfgFileReader;
import com.egoo.db.druid.connector.DBConnectionPool;
import com.egoo.druid.config.DBConfigure;
import com.egoo.local.config.ConfigInfo;
import org.quartz.Job;

/**
 * Created by liuchuan on 2017/7/3.
 */
public class ExcuteTask {
    private static	int DB_POOL = 1;
    public static ConfigInfo confInfo = new ConfigInfo();



    public static void main(String[] args){
        Task job = new Task();
        DBConfigure dbConfig = new DBConfigure(confInfo.DB_HOST, confInfo.DB_PORT, confInfo.DB_DATABASE, confInfo.DB_USER, confInfo.DB_PASSWD);
        DBConnectionPool.Start(dbConfig);
        System.out.println("【系统启动】");
        String time = new CfgFileReader("config.ini").getString("upload.time","0 0 22 * * ?");;
        JobUtil.addJob("Task",job,time);

       /* JobUtil.addJob("Task",job,"0 0 22-23 * * ?");
        JobUtil.addJob("Task",job,"0 0 0-6 * * ?");*/


    }
}
