import java.util.*;

public class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private int timeLimit;

    public Quiz(List<Question> questions, int timeLimit) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timeLimit = timeLimit;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getText());

            // Display options
            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i]);
            }

            // Implement timer
            long startTime = System.currentTimeMillis();
            boolean answered = false;
            while (!answered && (System.currentTimeMillis() - startTime) < timeLimit * 1000) {
                System.out.print("Enter your choice (1-" + currentQuestion.getOptions().length + "): ");
                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= currentQuestion.getOptions().length) {
                    answered = true;
                    if (choice == currentQuestion.getCorrectAnswerIndex() + 1) {
                        score++;
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect. The correct answer is: " + currentQuestion.getCorrectAnswer());
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + currentQuestion.getOptions().length);
                }
            }

            if (!answered) {
                System.out.println("Time's up! The correct answer is: " + currentQuestion.getCorrectAnswer());
            }

            currentQuestionIndex++;
            scanner.close();
        }

        // Display final score and summary
        System.out.println("Quiz completed!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
        System.out.println("Correct answers: " + score);
        System.out.println("Incorrect answers: " + (questions.size() - score));
    }

    // Class to represent a quiz question
    public static class Question {
        private String text;
        private String[] options;
        private int correctAnswerIndex;

        public Question(String text, String[] options, int correctAnswerIndex) {
            this.text = text;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getText() {
            return text;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }

        public String getCorrectAnswer() {
            return options[correctAnswerIndex];
        }
    }
}