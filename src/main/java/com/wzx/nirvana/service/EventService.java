package com.wzx.nirvana.service;

import com.wzx.nirvana.model.Event;
import com.wzx.nirvana.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    EventRepository eventRepository;

    public List<Event> calculateEvent(Date date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        List<Event> events = eventRepository.getEvents(date);
        List<Event> allEvents = new ArrayList<>();
        for (Event event : events) {
            allEvents.add(event);
            if (!(event.getRepeatCount() == null) && !(Objects.equals(event.getRepeatCount(), ""))) {
                for (int j = 1; j < Integer.parseInt(event.getRepeatCount()); j++) {
                    Event repeatEvent = new Event();
                    repeatEvent.setName(event.getName());
                    repeatEvent.setDescription(event.getDescription());
                    repeatEvent.setDuration(event.getDuration());
                    repeatEvent.setDateTime(df.format(getRepeatEventTime(event, j)));
                    allEvents.add(repeatEvent);
                }
            }
        }
        List<Event> eventsDay = new ArrayList<>();
        for (Event event : allEvents) {
            Date eventDate = df.parse(event.getDateTime());
            if (isSameDay(eventDate, date)) {
                eventsDay.add(event);
            }
        }
        return eventsDay;
    }

    public boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    public Date getRepeatEventTime(Event event, int count) throws ParseException {
        int repeatDay = 0;
        int repeatMonth = 0;
        switch (event.getRepeat()) {
            case "DAILY":
                repeatDay = 1;
                break;
            case "WEEKLY":
                repeatDay = 7;
                break;
            case "FORTNIGHTLY":
                repeatDay = 14;
                break;
            case "MONTHLY":
                repeatMonth = 1;
                break;
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        calendar.setTime(df.parse(event.getDateTime()));
        calendar.add(Calendar.MONTH, repeatMonth * count);
        calendar.add(Calendar.DAY_OF_YEAR, repeatDay * count);
        return calendar.getTime();
    }

}
