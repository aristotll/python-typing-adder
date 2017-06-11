import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import me.aristotll.Provider

import static liveplugin.PluginUtil.*


/**
 * Name: PythonTypeInfoAddedEvent<br>
 * User: Yao<br>
 * Date: 17/6/9<br>
 * Time: 15:10<br>
 */
class PythonTypeInfoAddedGroovyEvent {
    static final void eventClosure(AnActionEvent event) {

        //Get all the required data from data keys
        final Editor editor = event.getRequiredData(CommonDataKeys.EDITOR)
        final Project project = event.getRequiredData(CommonDataKeys.PROJECT)
        /**
         * http://www.jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi_files.html
         */
        PsiFile psiFile = currentPsiFileIn(project)
        final CaretModel cursor = editor.caretModel
        /**
         * 一般建议用父元素 一般都到底层的 token 了
         */
        PsiElement psiElement = psiFile.findElementAt(cursor.offset).parent
//        inspect(psiElement)
        if (psiElement == null) {
            return
        }

        //Access document, caret, and selection
        final Document document = editor.getDocument()
        final int line = cursor.logicalPosition.line
        //New instance of Runnable to make a replacement
        Runnable runnable = new Runnable() {
            @Override
            void run() {
                def type = Provider.getType(psiElement)
                if (type == null) {
                    return
                }
                document.insertString(document.getLineEndOffset(line),
                        "  # type: " + Provider.normalizeFunctionType(type))
            }
        }
        //Making the replacement
        WriteCommandAction.runWriteCommandAction(project, runnable)
    }
}
