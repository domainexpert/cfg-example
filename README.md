# cfg-example
Soot Jimple intraprocedural CFG execution tree traversal example

To run, say `ant run`. This will run the CFG traversal on its own `PathExplorer` class, printing out the Jimple statement that is visited at every level of the traversal tree. To compare the output with actual Jimple code, see the Jimple code of `PathExplorer` in the produced `soot_output` directory.
