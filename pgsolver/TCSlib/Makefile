ifeq ($(strip $(wildcard Config)),)
	include Config.default
else
	include Config
endif

INCLUDES=-I $(SRCDIR) -I $(OBJDIR) -I $(OCAML_DIR)

MODULES_DATA=$(OBJDIR)/tcsbasedata.cmx \
	$(OBJDIR)/tcsmaths.cmx \
	$(OBJDIR)/tcsarray.cmx \
	$(OBJDIR)/tcslist.cmx \
	$(OBJDIR)/tcsset.cmx \
	$(OBJDIR)/tcscache.cmx \
	$(OBJDIR)/tcsqueue.cmx \
	$(OBJDIR)/tcsstrings.cmx \
	$(OBJDIR)/tcsgraph.cmx

MODULES_UTILS=$(OBJDIR)/tcstiming.cmx \
	$(OBJDIR)/tcsstats.cmx \
	$(OBJDIR)/tcsargs.cmx \
	$(OBJDIR)/tcsmessage.cmx

MODULES_AUTOMATA=$(OBJDIR)/tcsautomata.cmx \
	$(OBJDIR)/tcsautotransform.cmx \
	$(OBJDIR)/tcsautohelper.cmx \
	$(OBJDIR)/tcsgames.cmx \
	$(OBJDIR)/tcstransitionsys.cmx \
	$(OBJDIR)/tcsparitygameparser.cmx \
	$(OBJDIR)/tcsparitysolutionparser.cmx \
	$(OBJDIR)/tcsltsparser.cmx \
	$(OBJDIR)/tcstsparser.cmx \
	$(OBJDIR)/tcsautoparser.cmx \
	$(OBJDIR)/tcsautomataparser.cmx \
	$(OBJDIR)/tcsgameparser.cmx \
	$(OBJDIR)/tcstransitionsysparser.cmx

MODULES_INTF=$(MODULES_DATA) \
	$(MODULES_UTILS) \
	$(MODULES_AUTOMATA)

PREMODULES=$(OBJDIR)/tcsautomataparserinternal.cmx \
           $(OBJDIR)/tcsgameparserinternal.cmx \
		   $(OBJDIR)/tcstransitionsysparserinternal.cmx \
		   $(OBJDIR)/tcsautomatalexer.cmx \
		   $(OBJDIR)/tcsparitygamelexer.cmx \
		   $(OBJDIR)/tcsparitysolutionlexer.cmx \
		   $(OBJDIR)/tcsltslexer.cmx \
		   $(OBJDIR)/tcstslexer.cmx

PREINTF=$(OBJDIR)/tcsautomataparserinternal.cmi \
        $(OBJDIR)/tcsgameparserinternal.cmi \
		$(OBJDIR)/tcstransitionsysparserinternal.cmi

MODULES=$(PREMODULES) $(MODULES_INTF)

INTERFACES=$(MODULES_INTF:.cmx=.cmi)

all: modules library

modules: $(PREINTF) $(INTERFACES) $(PREMODULES) $(MODULES)

LIBRARYNAME=$(OBJDIR)/tcslib.cmxa

library: modules libexec

libexec:
	$(OCAMLOPT) -a -o $(LIBRARYNAME) $(CPPCOMPILER) $(MODULES)

$(OBJDIR)/%.cmx: $(SRCDIR)/data/%.ml
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmi: $(SRCDIR)/data/%.mli
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmx: $(SRCDIR)/automata/%.ml
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmi: $(SRCDIR)/automata/%.mli
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmx: $(SRCDIR)/automata/parser/%.ml
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmi: $(SRCDIR)/automata/parser/%.mli
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmx: $(SRCDIR)/utils/%.ml
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(OBJDIR)/%.cmi: $(SRCDIR)/utils/%.mli
	$(OCAMLOPT) $(INCLUDES) -c -o $@ $<

$(SRCDIR)/automata/parser/tcsparitygameparser.mli: $(SRCDIR)/automata/parser/tcsparitygameparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsparitygameparser.mly

$(SRCDIR)/automata/parser/tcsparitygameparser.ml: $(SRCDIR)/automata/parser/tcsparitygameparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsparitygameparser.mly

$(SRCDIR)/automata/parser/tcsparitygamelexer.ml: $(SRCDIR)/automata/parser/tcsparitygamelexer.mll
	$(OCAMLLEX) $(SRCDIR)/automata/parser/tcsparitygamelexer.mll

$(SRCDIR)/automata/parser/tcsautoparser.mli: $(SRCDIR)/automata/parser/tcsautoparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsautoparser.mly

$(SRCDIR)/automata/parser/tcsautoparser.ml: $(SRCDIR)/automata/parser/tcsautoparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsautoparser.mly

$(SRCDIR)/automata/parser/tcsautomatalexer.ml: $(SRCDIR)/automata/parser/tcsautomatalexer.mll
	$(OCAMLLEX) $(SRCDIR)/automata/parser/tcsautomatalexer.mll

$(SRCDIR)/automata/parser/tcsparitysolutionparser.mli: $(SRCDIR)/automata/parser/tcsparitysolutionparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsparitysolutionparser.mly

$(SRCDIR)/automata/parser/tcsparitysolutionparser.ml: $(SRCDIR)/automata/parser/tcsparitysolutionparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsparitysolutionparser.mly

$(SRCDIR)/automata/parser/tcsparitysolutionlexer.ml: $(SRCDIR)/automata/parser/tcsparitysolutionlexer.mll
	$(OCAMLLEX) $(SRCDIR)/automata/parser/tcsparitysolutionlexer.mll

$(SRCDIR)/automata/parser/tcsltsparser.mli: $(SRCDIR)/automata/parser/tcsltsparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsltsparser.mly

$(SRCDIR)/automata/parser/tcsltsparser.ml: $(SRCDIR)/automata/parser/tcsltsparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcsltsparser.mly

$(SRCDIR)/automata/parser/tcsltslexer.ml: $(SRCDIR)/automata/parser/tcsltslexer.mll
	$(OCAMLLEX) $(SRCDIR)/automata/parser/tcsltslexer.mll

$(SRCDIR)/automata/parser/tcstsparser.mli: $(SRCDIR)/automata/parser/tcstsparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcstsparser.mly

$(SRCDIR)/automata/parser/tcstsparser.ml: $(SRCDIR)/automata/parser/tcstsparser.mly
	$(OCAMLYACC) $(SRCDIR)/automata/parser/tcstsparser.mly

$(SRCDIR)/automata/parser/tcstslexer.ml: $(SRCDIR)/automata/parser/tcstslexer.mll
	$(OCAMLLEX) $(SRCDIR)/automata/parser/tcstslexer.mll

clean:
	rm -f $(OBJDIR)/*.o \
	      $(OBJDIR)/*.cmx \
		  $(OBJDIR)/*.cmi \
		  $(SRCDIR)/automata/parser/tcsautoparser.ml \
		  $(SRCDIR)/automata/parser/tcsautoparser.mli \
		  $(SRCDIR)/automata/parser/tcsautomatalexer.ml \
		  $(SRCDIR)/automata/parser/tcsparitygameparser.ml \
		  $(SRCDIR)/automata/parser/tcsparitygameparser.mli \
		  $(SRCDIR)/automata/parser/tcsparitygamelexer.ml \
		  $(SRCDIR)/automata/parser/tcsparitysolutionparser.ml \
		  $(SRCDIR)/automata/parser/tcsparitysolutionparser.mli \
		  $(SRCDIR)/automata/parser/tcsparitysolutionlexer.ml \
		  $(SRCDIR)/automata/parser/tcsltsparser.ml \
		  $(SRCDIR)/automata/parser/tcsltsparser.mli \
		  $(SRCDIR)/automata/parser/tcsltslexer.ml \
		  $(SRCDIR)/automata/parser/tcstsparser.ml \
		  $(SRCDIR)/automata/parser/tcstsparser.mli \
		  $(SRCDIR)/automata/parser/tcstslexer.ml
