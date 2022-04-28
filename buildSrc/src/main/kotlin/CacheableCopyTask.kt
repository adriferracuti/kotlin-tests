import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.OutputFile
import javax.inject.Inject

abstract class CacheableCopyTask @Inject constructor(
    @get:InputFile
    val myInputFile: File
) : DefaultTask() {

    @get:OutputFile
    val out: File = File("build/resources/cacheable-copy-task-ouput")

    @TaskAction
    fun generate() {
        out.writeText(myInputFile.readText())
        println("out file written")
    }
}
