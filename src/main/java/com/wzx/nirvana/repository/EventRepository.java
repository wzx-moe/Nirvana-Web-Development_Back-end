package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository {

    List<Event> getEvents();

    List<Event> getEvents(Date date);

    Event addEvent(Event event);

    int updateEvent(Event event);

    long deleteEvent(String id);
}
