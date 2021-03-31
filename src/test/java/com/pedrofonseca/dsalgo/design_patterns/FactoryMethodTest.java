package com.pedrofonseca.dsalgo.design_patterns;

import com.pedrofonseca.dsalgo.design_patterns.factory_method.*;
import com.pedrofonseca.dsalgo.design_patterns.objects.OrcEnum;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class FactoryMethodTest {
    @Test
    public void testFactoryMethod() {
        ConcreteOrcFactory orcFactory = new ConcreteOrcFactory();

        assertNull(orcFactory.createOrc(null));
        assertEquals("Work, work.", orcFactory.createOrc(OrcEnum.PEON).work());
        assertEquals("Lok'tar.", orcFactory.createOrc(OrcEnum.WARRIOR).work());
        assertEquals("Wizards don't work, they study.", orcFactory.createOrc(OrcEnum.WIZARD).work());
    }
}
