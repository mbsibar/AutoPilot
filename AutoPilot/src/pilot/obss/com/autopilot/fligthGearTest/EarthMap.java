package pilot.obss.com.autopilot.fligthGearTest;
/*
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/
import java.util.ArrayList;
import java.util.List;
/*
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
*/
import pilot.obss.com.autopilot.util.types.GpsLocation;
/*
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;
*/
public class EarthMap {
	public static final int MIN_ZOOM = 0;
	public static final int MAX_ZOOM = 21;
	public static List<GpsLocation> paths = new ArrayList<GpsLocation>();
	public static Integer index = 0;
	/**
	 * In map.html file default zoom value is set to 4.
	 */
	private static int zoomValue = 4;

	public static void main(String[] args) {
//		System.setProperty("teamdev.license.info", "true");
//		final Browser browser = BrowserFactory.create();
//		GpsLocation location1 = new GpsLocation(32.1412312f, 110.1412312f, 1000f);
//
//		GpsLocation location2 = new GpsLocation();
//		location2.setLatitude(32.3412312f);
//		location2.setLongtitude(110.62312f);
//
//		GpsLocation location3 = new GpsLocation();
//		location3.setLatitude(32.4412312f);
//		location3.setLongtitude(110.72312f);
//
//		GpsLocation location4 = new GpsLocation();
//		location4.setLatitude(33.6512312f);
//		location4.setLongtitude(110.52312f);

//		paths.add(location1);
//		paths.add(location2);
//		paths.add(location3);
//		paths.add(location4);

//		JButton zoomInButton = new JButton("Zoom In");
//		zoomInButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (zoomValue < MAX_ZOOM) {
//					browser.executeJavaScript("map.setZoom(" + zoomValue + ")");
//				}
//			}
//		});
//
//		JButton zoomOutButton = new JButton("Zoom Out");
//		zoomOutButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (zoomValue > MIN_ZOOM) {
//					browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
//				}
//			}
//		});
//		
//		JButton setMarkerButton = new JButton("Set Marker");
//		setMarkerButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				browser.executeJavaScript("var path = poly.getPath(); \n" + 
//						"path.push(new google.maps.LatLng(" + paths.get(index).getLatitude() + ", " + paths.get(index).getLongtitude() + "));\n"
//				+ "var marker = new google.maps.Marker({" +
//						"position: new google.maps.LatLng(" + paths.get(index).getLatitude() + ", " + paths.get(index).getLongtitude() + ")," +
//						"title: '#deneme'," +
//						"map: map});"
//				
//				
//				);
//				index++;
//			}
//		});
//        
//        
//
//		JPanel toolBar = new JPanel();
//		toolBar.add(zoomInButton);
//		toolBar.add(zoomOutButton);
//		toolBar.add(setMarkerButton);
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
//		frame.add(toolBar, BorderLayout.NORTH);
//		frame.setSize(700, 500);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//
//		browser.loadURL("C:\\Users\\Ece\\Desktop\\GMap.html");
		
		
		double latB = 37.58756;
		double lonB = -122.29376;
		double latA = 37.58937f;
		double lonA = -122.25804f;
//
//		double Dlat = Math.log(Math.tan(latB / 2 + Math.PI / 4) / Math.tan(latA / 2 + Math.PI / 4));
//		double Dlon = Math.abs(lonA - lonB);
//		System.out.println(Dlat);
//		Dlat = Math.toDegrees(Dlat);
//		Dlon = Math.toDegrees(Dlon);
//		System.out.println(Dlat);
		
		
//		if (Dlon > 180) {
//			Dlon = Dlon % 180;
//		}
//		double degree = Math.toDegrees(Math.atan2(Dlon, Dlat));
		
		
		
//		φ is latitude, λ is longitude
		
		
		double y = Math.sin(lonB-lonA) * Math.cos(latB);
		double x = Math.cos(latA)*Math.sin(latB) -
		        Math.sin(latA)*Math.cos(latB)*Math.cos(lonB-lonA);
		double degree = Math.toDegrees(Math.atan2(y, x));
		if(degree<0){
			degree += 360;
		}
		
		System.out.println(degree);
	}
}