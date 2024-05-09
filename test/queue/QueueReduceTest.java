package queue;

import base.pairs.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.function.BinaryOperator;

public class QueueReduceTest extends QueueBaseTest<ArrayDeque<Object>> {
    @Override
    protected void otherOperations(final List<Pair<Queue, ArrayDeque<Object>>> queues, final Pair<Queue, ArrayDeque<Object>> pair) {
        final Object identity;
        final BinaryOperator<Object> accumulator;
        final String reductionRepresentation;
        if (random.nextBoolean()) {
            accumulator = (a, b) -> a.toString() + ", " + b.toString();
            identity = "";
            reductionRepresentation = "\"\", (a, b) -> a.toString() + \", \" + b.toString()";
        } else {
            accumulator = (a, b) -> a.hashCode() + b.hashCode();
            identity = 0;
            reductionRepresentation = "0, (a, b) -> a.hashCode() + b.hashCode()";
        }
        final Object actual = pair.first().reduce(identity, accumulator);
        final Object expected = pair.second().stream().reduce(identity, accumulator);
        Assert.assertEquals("Expected reduce(" + reductionRepresentation + ") to return the same", expected, actual);
    }

    @Test
    public void testArrayQueue() {
        test(ArrayQueue::new, ArrayDeque::new);
    }

    @Test
    public void testLinkedQueue() {
        test(LinkedQueue::new, ArrayDeque::new);
    }
}
