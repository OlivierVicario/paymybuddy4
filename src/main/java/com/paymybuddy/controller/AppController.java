package com.paymybuddy.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.paymybuddy.model.BankAccount;
import com.paymybuddy.model.Transaction;
import com.paymybuddy.model.TransactionFormData;
import com.paymybuddy.model.User;
import com.paymybuddy.service.BankAccountService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;

@Controller
public class AppController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);
	public User loggedUser;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private BankAccountService bankAccountService;

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		try {
			LOGGER.info("begin showDashboard");
			
			loggedUser = userService.getLoggedUser();
			model.addAttribute("user", loggedUser);
			TransactionFormData transactionFD = new TransactionFormData();
			transactionFD.setConnections(loggedUser.getUsersTo());
			model.addAttribute("transactionFD", transactionFD);
			List<Transaction> transactions = transactionService.getByUserFrom(loggedUser);
			model.addAttribute("transactions", transactions);
			return "dashboard_form";
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end showDashBoard");
		}

		return null;
	}

	@PostMapping("/process_paiement")
	public String payMyBuddy(TransactionFormData tfd) {
		try {
			LOGGER.info("begin payMyBuddy");
			
			String state = userService.manageTransaction(tfd);
			if (state.equals("success")) {
				return "transaction_success";
			} else {
				return "transaction_failure";
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end payMyBuddy");
		}

		return null;
	}
//*********************************** connections *****************************
	@GetMapping("/connections")
	public String showConnections(Model model) {
		try {
			LOGGER.info("begin showConnections");
			
			loggedUser = userService.getLoggedUser();
			List<User> connections = loggedUser.getUsersTo();
			model.addAttribute("connections", connections);
			return "connections_form";
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end showConnections");
		}

		return null;
	}

	@PostMapping("/process_add_connection")
	public String addConnection(String email) {
		try {
			LOGGER.info("begin addConnection");
			
			userService.addUserTo(email);
			//retourne la page mais vide
			return "connections_form";
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end addConnection");
		}

		return null;
	}
//*****************************************************************************
	@GetMapping("/profile")
	public ModelAndView showProfile() {
		try {
			LOGGER.info("begin showProfile");
			
			String viewName = "profile_form";
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			loggedUser = userService.getLoggedUser();
			model.put("user", loggedUser);
			
			BankAccount bankAccount = userService.findBankAccount(loggedUser);
			model.put("bankAccount", bankAccount);
			return new ModelAndView(viewName, model);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end showProfile");
		}

		return null;
	}

	@PostMapping("/update_profile")
	public ModelAndView updateProfile(User user) {
		try {
			LOGGER.info("begin updateProfile");
			
			userService.update(user);
			
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl("/dashboard");
			return new ModelAndView(redirectView);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end updateProfile");
		}
		return null;
	}

	@PostMapping("/update_bankaccount")
	public String updateBankAccount(BankAccount bankAccount) {
		try {
			LOGGER.info("begin updateBankAccount");
			bankAccountService.update(bankAccount);
			return "redirect:/dashboard";
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end updateBankAccount");
		}
		return null;
	}
//*****************************************************************************/

	/*****************************************************************************
		@GetMapping("/profile")
		public String showProfile(Model model) {
			try {
				LOGGER.info("begin showProfile");
				loggedUser = userService.getLoggedUser();
				model.addAttribute("user", loggedUser);
				BankAccount bankAccount = userService.findBankAccount(loggedUser);
				model.addAttribute("bankAccount", bankAccount);
				return "profile_form";
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			} finally {
				LOGGER.info("end showProfile");
			}

			return null;
		}

		@PostMapping("/update_profile")
		public String updateProfile(Model model) {
			try {
				LOGGER.info("begin updateProfile");
				User user = (User) model.getAttribute("user");
				userService.updateOrSave(user);
				return "profile_form";
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			} finally {
				LOGGER.info("end updateProfile");
			}
			return null;
		}

		@PostMapping("/update_bankaccount")
		public String updateBankAccount(BankAccount bankAccount) {
			try {
				LOGGER.info("begin updateBankAccount");
				bankAccountService.update(bankAccount);
				return "redirect:/dashboard";
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			} finally {
				LOGGER.info("end updateBankAccount");
			}
			return null;
		}
	//*****************************************************************************/
	
	
	
	
	
	@GetMapping("/credit_account")
	public String creditAccount(Model model) {
		try {
			LOGGER.info("begin creditAccount");
			
			String credit = "321321";
			model.addAttribute("credit", credit);
			return "creditAccount_form";
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end creditAccount");
		}
		return null;
	}

	@PostMapping("/update_balance")
	public String updateBalance(Model model) {
		try {
			LOGGER.info("begin updateBalance");
			
			loggedUser = userService.getLoggedUser();
			BankAccount bankAccount = userService.findBankAccount(loggedUser);
			String creditS = (String) model.getAttribute("credit").toString();
			BigDecimal credit = new BigDecimal(creditS);
			loggedUser.setBalance(loggedUser.getBalance().add(credit));
			return "creditAccount_form";
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("end updateBalance");
		}
		return null;
	}
//*****************************************************************************
}
