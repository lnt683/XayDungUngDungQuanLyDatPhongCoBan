package service.repository;

@FunctionalInterface
public interface RepositoryObserver {
    void update();
}