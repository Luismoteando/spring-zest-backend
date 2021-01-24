package es.upm.miw.spring_zest_backend.data_services;

import es.upm.miw.spring_zest_backend.documents.*;
import es.upm.miw.spring_zest_backend.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Service
public class DatabaseSeederService {

    private Environment environment;
    private WorkoutRepository workoutRepository;
    private MuscleRepository muscleRepository;
    private MuscleGroupRepository muscleGroupRepository;
    private ExerciseRepository exerciseRepository;
    private SessionRepository sessionRepository;
    private SessionExerciseRepository sessionExerciseRepository;

    @Autowired
    public DatabaseSeederService(
            Environment environment,
            WorkoutRepository workoutRepository,
            MuscleGroupRepository muscleGroupRepository,
            MuscleRepository muscleRepository,
            ExerciseRepository exerciseRepository,
            SessionRepository sessionRepository,
            SessionExerciseRepository sessionExerciseRepository
    ) {
        this.environment = environment;
        this.workoutRepository = workoutRepository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.muscleRepository = muscleRepository;
        this.exerciseRepository = exerciseRepository;
        this.sessionRepository = sessionRepository;
        this.sessionExerciseRepository = sessionExerciseRepository;
    }

    @PostConstruct
    public void constructor() {
        String[] profiles = this.environment.getActiveProfiles();
        if (Arrays.asList(profiles).contains("dev")) {
            this.deleteAllAndInitializeAndSeedDataBase();
        } else if (Arrays.asList(profiles).contains("prod")) {
            this.initialize();
        }
    }

    private void deleteAllAndInitializeAndSeedDataBase() {
        this.deleteAllAndInitialize();
        this.seedDataBaseJava();
    }

    private void deleteAllAndInitialize() {
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
        this.workoutRepository.deleteAll();
        this.muscleGroupRepository.deleteAll();
        this.muscleRepository.deleteAll();
        this.exerciseRepository.deleteAll();
        this.sessionRepository.deleteAll();
        this.sessionExerciseRepository.deleteAll();
        this.initialize();
    }

    private void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
        Workout[] workouts = {
                Workout.builder().name("Dumbell training").goal("Legs").duration(5400000L).build(),
                Workout.builder().name("Dumbell training").goal("Torso").duration(5400000L).build(),
                Workout.builder().name("Dumbell training").goal("Legs").duration(4500000L).build(),
                Workout.builder().name("Dumbell training").goal("Torso").duration(4500000L).build(),
        };
        this.workoutRepository.saveAll(Arrays.asList(workouts));
        LogManager.getLogger(this.getClass()).warn("        ------- workouts");
        Muscle[] muscles = {
                Muscle.builder().name("Biceps").build(),
                Muscle.builder().name("Triceps").build(),
                Muscle.builder().name("Forearms").build(),
                Muscle.builder().name("Trapezius").build(),
                Muscle.builder().name("Latissimus dorsi").build(),
                Muscle.builder().name("Fascia lata").build(),
                Muscle.builder().name("Buttocks").build(),
                Muscle.builder().name("Calves").build()
        };
        this.muscleRepository.saveAll(Arrays.asList(muscles));
        LogManager.getLogger(this.getClass()).warn("        ------- muscles");
        MuscleGroup[] muscleGroups = {
                MuscleGroup.builder().name("Arm").muscles(Arrays.asList(Arrays.copyOfRange(muscles, 0, 3))).build(),
                MuscleGroup.builder().name("Back").muscles(Arrays.asList(Arrays.copyOfRange(muscles, 3, 5))).build(),
                MuscleGroup.builder().name("Hip").muscles(Arrays.asList(Arrays.copyOfRange(muscles, 5, 7))).build(),
                MuscleGroup.builder().name("Leg").muscles(Collections.singletonList(muscles[7])).build(),
        };
        this.muscleGroupRepository.saveAll(Arrays.asList(muscleGroups));
        LogManager.getLogger(this.getClass()).warn("        ------- muscle groups");
        Exercise[] exercises = {
                Exercise.builder().name("Biceps dumbbell curl").muscles(Collections.singletonList(muscles[0])).workout(workouts[1]).build(),
                Exercise.builder().name("High pulley triceps").muscles(Collections.singletonList(muscles[1])).workout(workouts[1]).build(),
                Exercise.builder().name("Forearms dumbbell curl").muscles(Collections.singletonList(muscles[2])).workout(workouts[1]).build(),
                Exercise.builder().name("Pushup").muscles(Collections.singletonList(muscles[3])).workout(workouts[3]).build(),
                Exercise.builder().name("Barbell rows").muscles(Collections.singletonList(muscles[4])).workout(workouts[3]).build(),
                Exercise.builder().name("Forearms dumbbell curl").muscles(Collections.singletonList(muscles[2])).workout(workouts[3]).build(),
                Exercise.builder().name("Ball hip abduction").muscles(Collections.singletonList(muscles[5])).workout(workouts[0]).build(),
                Exercise.builder().name("Squats").muscles(Collections.singletonList(muscles[6])).workout(workouts[0]).build(),
                Exercise.builder().name("Tiptoe in step").muscles(Collections.singletonList(muscles[7])).workout(workouts[0]).build(),
                Exercise.builder().name("Ball hip abduction").muscles(Collections.singletonList(muscles[5])).workout(workouts[2]).build(),
                Exercise.builder().name("Squats").muscles(Collections.singletonList(muscles[6])).workout(workouts[2]).build(),
                Exercise.builder().name("Tiptoe in step").muscles(Collections.singletonList(muscles[7])).workout(workouts[2]).build()
        };
        this.exerciseRepository.saveAll(Arrays.asList(exercises));
        LogManager.getLogger(this.getClass()).warn("        ------- exercises");
        SessionExercise[] sessionExercises = {
                SessionExercise.builder().exercise(exercises[0]).order(1).repetitions("6-12").sets(3).weight(10.0).build(),
                SessionExercise.builder().exercise(exercises[1]).order(2).repetitions("6-12").sets(3).weight(30.0).build(),
                SessionExercise.builder().exercise(exercises[2]).order(3).repetitions("6-12").sets(3).weight(5.0).build(),
                SessionExercise.builder().exercise(exercises[3]).order(1).repetitions("6-12").sets(3).weight(0.0).build(),
                SessionExercise.builder().exercise(exercises[4]).order(2).repetitions("6-12").sets(3).weight(20.0).build(),
                SessionExercise.builder().exercise(exercises[2]).order(3).repetitions("6-12").sets(3).weight(5.0).build(),
                SessionExercise.builder().exercise(exercises[5]).order(1).repetitions("6-12").sets(3).weight(0.0).build(),
                SessionExercise.builder().exercise(exercises[6]).order(2).repetitions("6-12").sets(3).weight(20.0).build(),
                SessionExercise.builder().exercise(exercises[7]).order(3).repetitions("6-12").sets(3).weight(0.0).build(),
                SessionExercise.builder().exercise(exercises[5]).order(1).repetitions("6-12").sets(3).weight(0.0).build(),
                SessionExercise.builder().exercise(exercises[6]).order(2).repetitions("6-12").sets(3).weight(20.0).build(),
                SessionExercise.builder().exercise(exercises[7]).order(3).repetitions("6-12").sets(3).weight(0.0).build()
        };
        this.sessionExerciseRepository.saveAll(Arrays.asList(sessionExercises));
        LogManager.getLogger(this.getClass()).warn("        ------- session exercises");
        Session[] sessions = {
                Session.builder().title("Torso Day 1")
                        .start(new Date())
                        .sessionExercises(Arrays.asList(Arrays.copyOfRange(sessionExercises, 0, 3))).build(),
                Session.builder().title("Torso Day 2")
                        .start(new Date())
                        .sessionExercises(Arrays.asList(Arrays.copyOfRange(sessionExercises, 3, 6))).build(),
                Session.builder().title("Leg Day 1")
                        .start(new Date())
                        .sessionExercises(Arrays.asList(Arrays.copyOfRange(sessionExercises, 6, 9))).build(),
                Session.builder().title("Torso Day 1")
                        .start(new Date())
                        .sessionExercises(Arrays.asList(Arrays.copyOfRange(sessionExercises, 9, 12))).build()
        };
        this.sessionRepository.saveAll(Arrays.asList(sessions));
        LogManager.getLogger(this.getClass()).warn("        ------- sessions");
    }

    private void initialize() {
    }
}



