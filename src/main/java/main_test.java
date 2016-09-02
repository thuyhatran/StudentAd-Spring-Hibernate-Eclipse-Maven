import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.studentadm.dao.CourseDao;
import com.studentadm.model.Course;
import com.studentadm.service.CourseService;

public class main_test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	
		
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext-servlet.xml");
		 
		 /*		 CourseService crsService = context.getBean("courseService",CourseService.class);
		 

		 
		 Course crs = new Course(4,"Database");
		 crsService.insert(crs);
		 
		 
		System.out.println("New course ID: " + crsService.getNewCourseID()); 
		System.out.println(" course : " +crsService.selectById(2));
		
		System.out.println(crsService.select().get(0));*/
		
		
		
		 
	}

}
