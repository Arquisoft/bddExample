package es.uniovi.asw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Controller
public class Main {

  private Random random = new Random();
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @RequestMapping("/")
  public ModelAndView landing(Model model) {
    return new ModelAndView("landing");
  }

  long sortStream(int times, int size, Random random) {
    LOG.info("Sorting " + size + " random elements " + times + "times");
    long millis0 = System.currentTimeMillis();
    IntStream.range(1, times).forEach(i -> {
        IntStream randoms = IntStream.range(1, size).map(j -> random.nextInt());
        Arrays.asList(randoms.sorted().toArray());
    });
    long millis1 = System.currentTimeMillis();
    long elapsed = millis1 - millis0;
    LOG.info("Elapsed: " + elapsed);
    return elapsed;
  }

  @RequestMapping("/sort")
  public ModelAndView sort() {
    int times = 500 + random.nextInt(500);
    int size = 1000 + random.nextInt(10000);
    long elapsed = sortStream(times,size,random);
    ModelAndView model = new ModelAndView("sort");
    model.addObject("size", size);
    model.addObject("times", times);
    model.addObject("elapsed", elapsed);
    return model;
  }

  @RequestMapping(path ="/search", method = RequestMethod.GET)
  public ModelAndView search(@RequestParam("name") String name) {
    LOG.info("Searching..." + name);
    long elapsed = 0;
    switch (name) {
        case "long": elapsed = sortStream(10000,10000, random);
        break;
        case "error":
            throw new RuntimeException("Name 'error' not found");
        default: elapsed = sortStream(random.nextInt(500),random.nextInt(10000), random);
    }
    ModelAndView model = new ModelAndView("search");
    model.addObject("name",name);
    model.addObject("elapsed",elapsed);
    return model;
  }
}