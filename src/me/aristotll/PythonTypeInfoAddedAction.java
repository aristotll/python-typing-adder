package me.aristotll;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Name: PythonTypeInfoAddedAction<br>
 * User: Yao<br>
 * Date: 17/6/11<br>
 * Time: 20:22<br>
 */
public class PythonTypeInfoAddedAction extends AnAction {
    /**
     * Implement this method to provide your action handler.
     *
     * @param e Carries information on the invocation place
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        PythonTypeInfoAddedEvent.eventClosure(e);
    }

}
