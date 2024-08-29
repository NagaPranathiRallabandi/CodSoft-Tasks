import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

    // Class to store quiz questions
    static class QuizQuestion {
        private String question;
        private String[] options;
        private int correctAnswerIndex;

        public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }
    }

    // Main class to manage the quiz
    private QuizQuestion[] questions;
    private int score;
    private int currentQuestionIndex;
    private boolean timeUp;
    private static final int TIME_LIMIT = 10; // seconds

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.timeUp = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            timeUp = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                }
            }, TIME_LIMIT * 1000);

            System.out.println(question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ": " + options[i]);
            }

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            timer.cancel();

            if (timeUp) {
                System.out.println("Time's up!");
            } else if (userAnswer == question.getCorrectAnswerIndex() + 1) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was " + options[question.getCorrectAnswerIndex()]);
            }

            currentQuestionIndex++;
            System.out.println();
        }

        scanner.close();
        displayResults();
    }

    private void displayResults() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        QuizQuestion[] questions = new QuizQuestion[] {
            new QuizQuestion("What is the capital of France?", new String[] {"Berlin", "Paris", "Rome", "Madrid"}, 1),
            new QuizQuestion("What is 2 + 2?", new String[] {"3", "4", "5", "6"}, 1),
            new QuizQuestion("Which planet is known as the Red Planet?", new String[] {"Earth", "Mars", "Jupiter", "Venus"}, 1)
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}