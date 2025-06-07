import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChatBotGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private HashMap<String, String> knowledgeBase;

    public ChatBotGUI() {
        setTitle("Java ChatBot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Initialize knowledge base
        knowledgeBase = new HashMap<>();
        trainBot();

        // Event handling
        sendButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());
    }

    private void trainBot() {
        // Rule-based training with FAQs
        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("hello", "Hi there! Ask me anything.");
        knowledgeBase.put("how are you", "I'm just code, but thanks for asking!");
        knowledgeBase.put("what is java", "Java is a popular object-oriented programming language.");
        knowledgeBase.put("what is your name", "I'm JavaBot, your study buddy.");
        knowledgeBase.put("bye", "Goodbye! Have a great day.");
        knowledgeBase.put("thank you", "You're welcome!");
    }

    private void processInput() {
        String userInput = inputField.getText().trim().toLowerCase();
        chatArea.append("You: " + userInput + "\n");

        String botResponse = getResponse(userInput);
        chatArea.append("Bot: " + botResponse + "\n\n");

        inputField.setText("");
    }

    private String getResponse(String input) {
        // Simple NLP: token normalization and keyword matching
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "Sorry, I didn't understand that. Try asking something else!";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatBotGUI().setVisible(true));
    }
}