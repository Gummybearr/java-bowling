package qna.domain;

import qna.service.DeleteHistoryService;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {

    private DeleteHistoryService deleteHistoryService;

    private List<DeleteHistory> deleteHistories;

    public DeleteHistoryList(DeleteHistoryService deleteHistoryService) {
        this.deleteHistories = new ArrayList<>();
        this.deleteHistoryService = deleteHistoryService;
    }

    public List<DeleteHistory> delete(Question question) {
        deleteQuestion(question);
        for (Answer answer : question.getAnswers()) {
            deleteAnswer(answer);
        }
        deleteHistoryService.saveAll(this.deleteHistories);
        return this.deleteHistories;
    }

    public DeleteHistory deleteQuestion(Question question) {
        question.delete();
        DeleteHistory deleteHistory = new DeleteHistory(question);
        this.deleteHistories.add(deleteHistory);
        return deleteHistory;
    }

    public DeleteHistory deleteAnswer(Answer answer) {
        answer.delete();
        DeleteHistory deleteHistory = new DeleteHistory(answer);
        this.deleteHistories.add(deleteHistory);
        return deleteHistory;
    }

}
