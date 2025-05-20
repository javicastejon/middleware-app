package com.tfg.backend.models.Deprecated;
// package com.tfg.backend.models;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "Meeting_Player")
// public class MeetingPlayer {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "meeting_player_id")
//     private Integer meeting_player_id;

//     @ManyToOne
//     @JoinColumn(name = "fk_meeting", nullable = false)
//     private Meeting fkMeeting;

//     @ManyToOne
//     @JoinColumn(name = "fk_user", nullable = false)
//     private User fkUser;

//     @Column(name = "meeting_winner", columnDefinition = "   BOOLEAN NOT NULL")
//     private Boolean meeting_winner;

//     public MeetingPlayer() {
//     }

//     public Integer getMeeting_player_id() {
//         return meeting_player_id;
//     }

//     public void setMeeting_player_id(Integer meeting_player_id) {
//         this.meeting_player_id = meeting_player_id;
//     }

//     public Meeting getFkMeeting() {
//         return fkMeeting;
//     }

//     public void setFkMeeting(Meeting fkMeeting) {
//         this.fkMeeting = fkMeeting;
//     }

//     public User getFkUser() {
//         return fkUser;
//     }

//     public void setFkUser(User fkUser) {
//         this.fkUser = fkUser;
//     }

//     public Boolean getMeeting_winner() {
//         return meeting_winner;
//     }

//     public void setMeeting_winner(Boolean meeting_winner) {
//         this.meeting_winner = meeting_winner;
//     }
// }
