This was an in-class example to demonstrate Eclipse runtime debugging features.

The mistake is one I actually made while doing a C++ shunting-yard implementation without debugging tools that took way too long to diagnose without them, so I thought it made a good example.

If you actually want to know the mistake I was testing (which is fixed in this code!) I was returning a new ArrayList at the bottom of the method instead of the resultant one.

It was a holdover from earlier-on placeholder code and I thought it was a classic mistake that can be easily debugged around, but might (and for me, did!) escape notice without such tools.