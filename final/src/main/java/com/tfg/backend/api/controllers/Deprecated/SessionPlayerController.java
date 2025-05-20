package com.tfg.backend.api.controllers.Deprecated;
// package com.tfg.backend.api;

// import com.tfg.backend.api.request.SessionRequest;

// @RestController
// @RequestMapping("/tc_api/v1/session/players")
// @CrossOrigin(origins = "*")
// public class SessionPlayerController {

//     private final SessionPlayerService sessionService;

//     @Autowired
//     public SessionPlayerController(SessionPlayerService sessionService) {
//         this.sessionService = sessionService;
//     }
    
//     // Add Session by User
//     @PostMapping("/new")
//     public ResponseEntity<?> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        
//         sessionService.createSession(sessionRequest);       
//         return new ResponseEntity<>(HttpStatus.CREATED);       
//     }

    
//     // Update Session by User
//     @PutMapping("/{id_session}")
//     public ResponseEntity<?> updateSession(
//             @PathVariable Integer id_session,
//             @Valid @RequestBody SessionRequest sessionRequest) {
//         sessionService.updateSession(id_session, sessionRequest);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     // Delete Session by User
//     @Transactional
//     @DeleteMapping("/{id_session}")
//     public ResponseEntity<?> deleteSession(@PathVariable Integer id_session) {
//         sessionService.deleteSession(id_session);
//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     // Get all Session by user
//     @GetMapping("/all/{id_user}")
//     public ResponseEntity<List<Session>> getSessionsByHost(@PathVariable Integer id_user) {
//         List<Session> sessions = sessionService.getSessionsByHost(id_user);
//         return new ResponseEntity<>(sessions, HttpStatus.OK);
//     }
// }
