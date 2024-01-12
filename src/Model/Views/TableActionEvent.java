package Model.Views;

/**
 *
 * @author Hitler
 */
public interface TableActionEvent {

    default void onEdit(int row) {

    };
    
    default void onDelete(int row){
        
    };
    
    default void onComment(int row) {
        
    };
}
