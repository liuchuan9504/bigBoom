import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by liuchuan on 2017/7/3.
 */
public class Task implements Job {
    //@Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            System.out.println("上传任务执行中");
           // ds.DB_Connection();
            DBScan.DB_Upload();



        }catch (Exception ex ){

        }
        //System.out.print("");

    }


}
