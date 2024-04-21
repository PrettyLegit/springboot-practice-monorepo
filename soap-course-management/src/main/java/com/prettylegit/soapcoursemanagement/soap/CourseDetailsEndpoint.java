package com.prettylegit.soapcoursemanagement.soap;

import net.jimmywin.courses.*;

import com.prettylegit.soapcoursemanagement.soap.bean.Course;
import com.prettylegit.soapcoursemanagement.soap.service.CourseDetailsService;
import com.prettylegit.soapcoursemanagement.soap.service.CourseDetailsService.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;
    //method
    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://jimmywin.net/courses
    // name - GetCourseDetailsRequest
    @PayloadRoot(namespace = "http://jimmywin.net/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId());

        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = "http://jimmywin.net/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courses = service.findAll();

        return mapAllCourseDetails(courses);
    }

    @PayloadRoot(namespace = "http://jimmywin.net/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processDeleteRequest(@RequestPayload DeleteCourseDetailsRequest request){
        Status status = service.deleteById(request.getId());
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));
        return response;
    }

    private net.jimmywin.courses.Status mapStatus(Status status){
        if(status == Status.FAILURE){
            return net.jimmywin.courses.Status.FAILURE;
        }
        return net.jimmywin.courses.Status.SUCCESS;
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for(Course course:courses){
            CourseDetails mapCourse = mapCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        return response;
    }

    private static CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());

        courseDetails.setName(course.getName());

        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
}
