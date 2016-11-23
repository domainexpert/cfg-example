# cfg-example
Soot Jimple intraprocedural CFG execution tree traversal example

See LICENSE.md for license information.

To run, you need Soot. First, download [soot-trunk.jar](https://ssebuild.cased.de/nightly/soot/lib/soot-trunk.jar) into `lib` directory. Now say `ant run`. This will run the CFG traversal on its own `PathExplorer` class, printing out the Jimple statement that is visited at every level of the traversal tree. To compare the output with actual Jimple code, see the Jimple code of `PathExplorer` in the produced `soot_output` directory.
