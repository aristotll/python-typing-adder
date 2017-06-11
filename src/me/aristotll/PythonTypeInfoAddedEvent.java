package me.aristotll;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

/**
 * Name: PythonTypeInfoAddedEvent<br>
 * User: Yao<br>
 * Date: 17/6/9<br>
 * Time: 15:10<br>
 */
public class PythonTypeInfoAddedEvent {
    public static void eventClosure(@NotNull AnActionEvent event) {

        //Get all the required data from data keys
        final Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = event.getRequiredData(CommonDataKeys.PROJECT);
        /*
         * http://www.jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi_files.html
         */
        final VirtualFile file = ((FileEditorManagerEx) FileEditorManagerEx.getInstance(project)).getCurrentFile();
        if (file == null) {
            return;
        }
        PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
        final CaretModel cursor = editor.getCaretModel();
        if (psiFile == null) {
            return;
        }
        final PsiElement element = psiFile.findElementAt(cursor.getOffset());
        if (element == null) {
            return;
        }
        final PsiElement psiElement = element.getParent();
        if (psiElement == null) {
            return;
        }


        //Access document, caret, and selection
        final Document document = editor.getDocument();
        final int line = cursor.getLogicalPosition().line;
        //New instance of Runnable to make a replacement
        Runnable runnable = () -> {
            String type = Provider.getType(psiElement);
            if (type == null) {
                return;

            }

            document.insertString(document.getLineEndOffset(line), "  # type: " + Provider.normalizeFunctionType(type));
        };
        //Making the replacement
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }

}
