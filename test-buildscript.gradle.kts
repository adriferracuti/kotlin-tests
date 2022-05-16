val miao = 5
extra.properties["miao"] = miao
println("test buildscript started")

println("miao is null ? ${miao == null}")
println("extra miao is null ? ${extra.properties["miao"] == null}")
println("project extra miao is null ? ${project.extra.properties["miao"] == null}")
