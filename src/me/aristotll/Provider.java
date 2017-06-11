package me.aristotll;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.documentation.PythonDocumentationProvider;
import com.jetbrains.python.psi.PyTypedElement;
import com.jetbrains.python.psi.types.TypeEvalContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Provider {
    @Nullable
    public static String getType(@NotNull PsiElement originalElement) {
        if (originalElement instanceof PyTypedElement) {
            TypeEvalContext context = TypeEvalContext.userInitiated(originalElement.getProject(), originalElement.getContainingFile());
            return PythonDocumentationProvider.getTypeName(context.getType((PyTypedElement) originalElement), context);
        }
        return null;
    }

    @NotNull
    public static String normalizeFunctionType(@NotNull String s) {
        final int index = s.indexOf("(");
        if (index > -1) {
            final int ending = s.indexOf(")") + 1;
            String parameterInfo = s.substring(index, ending);
            return parameterInfo.replaceAll(",.*?:", ",").replaceAll("\\(.*?: ", "(") + s.substring(ending);
        }


        return s;
    }

}
