package test;

import com.item.hive.entity.DbInfo;
import com.item.hive.service.MyHiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@RestController
public class MyApp {

    @Resource(name = "myHive")
    private MyHiveService myHiveService;


    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args);
    }

    @GetMapping
    public String hello(){
        try {
            List<DbInfo> allDataBases = myHiveService.getAllDataBases();
            System.out.println(allDataBases.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "hello word";
    }
}
