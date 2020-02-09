package tk.laurenfrost.users.service;

import org.springframework.stereotype.Service;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Contact;
import tk.laurenfrost.users.repository.ContactRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    final
    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> addContacts(List<Contact> contacts) {
        return contactRepository.saveAll(contacts);
    }

    public List<Contact> updateContacts(List<Contact> contacts) {
//        contactRepository.findAllById(
//                contacts.stream().map(Contact::getId).collect(Collectors.toList())
//        ).stream().map(Contact::getId).collect(Collectors.toList());
        return addContacts(contacts);
    }

//    public List<Contact> findExistingContacts(List<Contact> contacts) {
//        return contactRepository.findAllById(
//                contacts.stream().map(Contact::getId).collect(Collectors.toList())
//        );
//    }

    public List<Contact> getUserContacts(AppUser user) {
        return contactRepository.findByUser(user);
    }
}
