package it.voxsim.message;

import java.util.Calendar;

public class EnglishDeltaTimeTranslator implements DeltaTimeTranslator {

	public String translate(Calendar endTime, Calendar startTime) {
		Long delta = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		return formatDelta(delta) + " ago";
	}

	// TODO 9. Object Calisthenics - 3 levels of indentation,
	// hard coded values,
	// 2 responsibilities (calculation and printing)
	// 2 methods to extract
	private String formatDelta(Long delta) {
		Integer numberOfSeconds = (int) (delta / 1000);
		Integer numberOfMinutes = numberOfSeconds / 60;
		Integer numberOfHours = numberOfMinutes / 60;
		Integer numberOfDays = numberOfHours / 24;

		TimeDescription[] timeDescriptions = new TimeDescription[] {
				new TimeDescription(numberOfDays, TimeType.DAY),
				new TimeDescription(numberOfHours, TimeType.HOUR),
				new TimeDescription(numberOfMinutes, TimeType.MINUTE),
				new TimeDescription(numberOfSeconds, TimeType.SECOND)
		};

		for (TimeDescription timeDescription : timeDescriptions)
			if (timeDescription.isNotZero())
				return timeDescription.description();

		return TimeDescription.ZERO_SECOND.description();
	}
}