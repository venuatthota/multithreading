package courses.lecture1;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;

public class HardwareUtility {
	public static long getCPUCores(){
		return Runtime.getRuntime().availableProcessors();
	}
	
	public static void main (String[] args){
		 Kernel32 INSTANCE = (Kernel32) Native
			        .loadLibrary("Kernel32", Kernel32.class);

			           SYSTEMTIME time =new SYSTEMTIME();
			           INSTANCE.GetSystemTime(time);
			           System.out.println("Day of the Week "+time.wDayOfWeek);
			           System.out.println("Year :  "+time.wYear);
			           
			           SYSTEM_INFO systeminfo=new SYSTEM_INFO();
			           
			           INSTANCE.GetSystemInfo(systeminfo);
			         //  INSTANCE.GetNativeSystemInfo(arg0);
			           System.out.println("Processor Type : "+systeminfo.dwNumberOfProcessors);
	}
}
