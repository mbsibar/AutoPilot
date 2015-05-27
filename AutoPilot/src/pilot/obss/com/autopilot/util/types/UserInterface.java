package pilot.obss.com.autopilot.util.types;

import pilot.obss.com.autopilot.util.types.CraftInformation;

public interface UserInterface {

	void updateCraftInfo(CraftInformation craftInformation);

	void displayMission();

    void cycle(double hertz);

    void writeToTextView(final String message);

    void writeToTextViewP(final String message);

    void writeToTextViewI(final String message);

    void writeToTextViewD(final String message);

}
