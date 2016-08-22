import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.studentadm.dao.courseDao;
import com.studentadm.model.Course;
import com.studentadm.service.courseService;

public class main_test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		//courseService crsService = new courseService();
		
		//System.out.println("New course ID: " + crsService.getNewCourseID());
		
		//System.out.println("New course : " +crsService.selectById(2));
		
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		 
		 courseService crsService = context.getBean("courseService",courseService.class);
		 
		/*courseDao crsDao = context.getBean("courseDao",courseDao.class);
		
		System.out.println("New course : " +crsDao.selectById(2));*/
		 
		/* Course crs = new Course(4,"Database");
		 crsService.insert(crs);
		 */
		//System.out.println(" course : " +crsService.selectById(2));
		 
		 

	}

}
