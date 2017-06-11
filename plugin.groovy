/**
 * Name: PythonTypeInfoAdded<br>
 * User: Yao<br>
 * Date: 17/6/9<br>
 * Time: 15:10<br>
 */

import liveplugin.PluginUtil

import static liveplugin.PluginUtil.registerAction
import static liveplugin.PluginUtil.show

// depends-on-plugin  Pythonid
//if (isIdeStartup) return


// Y for python type
PluginUtil.registerAction("LPPythonTypeInfoAdded", "ctrl shift Y", new PythonTypeInfoAddedGroovyAction())


if (!isIdeStartup) show("LPPythonTypeInfoAdded:Loaded")




