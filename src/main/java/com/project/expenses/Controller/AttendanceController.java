package com.project.expenses.Controller;

import com.project.expenses.Model.Attendance;
import com.project.expenses.Model.User;
import com.project.expenses.Repository.AttendanceRepository;
import com.project.expenses.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceController(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/checkin/{userId}")
    public ResponseEntity<String> markCheckIn(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("User not found");

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(today);
        attendance.setCheckIn(now);
        attendanceRepository.save(attendance);

        return ResponseEntity.ok("Check-in recorded");
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<String> markCheckOut(@PathVariable Long userId) {
        List<Attendance> records = attendanceRepository.findByUserIdAndDate(userId, LocalDate.now());
        if (records.isEmpty()) {
            return ResponseEntity.badRequest().body("Check-in not found for today");
        }

        Attendance todayAttendance = records.get(0);
        if (todayAttendance.getCheckOut() != null) {
            return ResponseEntity.ok("Already checked out today");
        }

        todayAttendance.setCheckOut(LocalDateTime.now());
        attendanceRepository.save(todayAttendance);

        return ResponseEntity.ok("Check-out recorded");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Attendance>> getAttendanceByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(attendanceRepository.findByUserId(userId));
    }
}
