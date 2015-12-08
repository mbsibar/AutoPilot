package pilot.obss.com.autopilot.util;

import java.util.ArrayList;
import java.util.List;

import pilot.obss.com.autopilot.util.constants.CommonConstants;
import pilot.obss.com.autopilot.util.types.GpsLocation;
import pilot.obss.com.autopilot.util.types.WayPoint;

public class Mission {
	private List<WayPoint> wayPointList;
	private boolean completed;
	private int wayPointIterator;

	private float R = 6372.795477598f; 

	public Mission() {
		completed = false;
		wayPointIterator = 0;
		wayPointList = new ArrayList<WayPoint>();
	}

	public boolean isCompleted() {
		return completed;
	}

	public WayPoint getCurrentWayPoint() {
		if (wayPointList.size() < wayPointIterator || wayPointList.size() == 0) {
			return null;
		}
		return wayPointList.get(wayPointIterator);
	}

	public void setCurrentPosition(GpsLocation gpsLocation) {
		if (getCurrentWayPoint() != null) {
			if (getDistance(gpsLocation) <= CommonConstants.GPS_TOLERANCE) {
				getCurrentWayPoint().setCompleted(true);
				wayPointIterator++;
			}
		}
	}

	private double getDistance(GpsLocation currentGpsLocation) {
		GpsLocation nextGpsLocation = getCurrentWayPoint().getGpsLocation();
		double latB = nextGpsLocation.getLatitude();
		double lonB = nextGpsLocation.getLongtitude();
		double latA = currentGpsLocation.getLatitude();
		double lonA = currentGpsLocation.getLongtitude();
		return R * Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(lonA - lonB));
	}

	public double getBearing(GpsLocation gpsLocation) {
		GpsLocation waypointLocation = getCurrentWayPoint().getGpsLocation();
		double latB = waypointLocation.getLatitude();
		double lonB = waypointLocation.getLongtitude();
		double latA = gpsLocation.getLatitude();
		double lonA = gpsLocation.getLongtitude();

		double y = Math.sin(lonB - lonA) * Math.cos(latB);
		double x = Math.cos(latA) * Math.sin(latB) - Math.sin(latA) * Math.cos(latB) * Math.cos(lonB - lonA);
		double degree = Math.toDegrees(Math.atan2(y, x));
		if (degree < 0) {
			degree += 360;
		}
		return degree;
	}

	public void addNewLocation(GpsLocation gpsLocation) {
		WayPoint wayPoint = new WayPoint();
		wayPoint.setCompleted(false);
		wayPoint.setGpsLocation(gpsLocation);
		wayPointList.add(wayPoint);
	}

	public List<WayPoint> getWayPointList() {
		return wayPointList;
	}

}
