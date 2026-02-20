package com.kce.main;

import java.util.Scanner;

import com.kce.bean.Enrollment;
import com.kce.service.EnrollmentService;

public class HibernateMain {
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        enrollmentService = new EnrollmentService();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- University Enrollment Console ---");

        try {
            boolean r =enrollmentService.enrollStudent("CSE101","ST4001","Megha Iyer");
            System.out.println(r ? "ENROLLED" : "FAILED");
        } catch(Exception e) {
        	System.out.println(e); 
        }

        try {
            boolean r = enrollmentService.dropEnrollment(50001);
            System.out.println(r ? "DROPPED" : "FAILED");
        } catch(Exception e) { 
        	System.out.println(e); 
        }

        sc.close();
    }
}

